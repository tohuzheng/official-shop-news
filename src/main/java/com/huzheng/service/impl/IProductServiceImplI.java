package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.dao.IProductDao;
import com.huzheng.dto.ProductDetailDto;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.Discount;
import com.huzheng.entity.Product;
import com.huzheng.entity.SinglePromotion;
import com.huzheng.service.*;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * (Product)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-11 21:24:11
 */
@Service("productService")
public class IProductServiceImplI extends IBaseServiceImpl< IProductDao, Product> implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IBuyOrderDao buyOrderDao;

    @Autowired
    private ICouponService couponService;
    @Autowired
    private ICouponProductRelationService couponProductRelationService;
    @Autowired
    private ICouponProductCategoryRelationService categoryRelationService;
    @Autowired
    private ISinglePromotionService singlePromotionService;
    @Autowired
    private IDiscountService discountService;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productDao.deleteByIdProduct(id) > 0;
    }
    
     /**
     * 通过实体作为where条件查询
     *
     * @param product 条件
     * @return 查询结果集合
     */
    @Override
    public List<Product> queryAllByCondition(Product product) {
        return this.productDao.queryAll(product);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/18 13:26
     * @description 通过实体类体条件分页查询
     */
    @Override
    public IPage<Product> queryPageByEntity(Page page, Product product) {
        QueryWrapper<Product> queryWrapper =new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(product);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);
        IPage iPage = this._selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/1 0:03
     * @description 上架时间最新的6个商品
     * @param
     */
    @Override
    public List<Product> queryNewProduct() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Product> page = new Page<>();
        page.setSize(6);
        IPage<Product> productIPage = this._selectPage(page, queryWrapper);
        return productIPage.getRecords();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/1 10:16
     * @description 获取销量最多的商品6个
     * @param
     */
    @Override
    public List<Product> queryRecommendProduct() {
        List<Integer> skuIds = buyOrderDao.querySellMaxSex();
        List<Product> productList = new ArrayList<>();
        for (Integer id : skuIds) {
            Product product = productDao.selectById(id);
            if (product != null) {
                productList.add(product);
            }
        }

        return productList;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/6 16:56
     * @description 解析excel，根据excel中的商品id查询商品信息
     * @param excelPath
     */
    @Override
    public List<Product> queryProductByExcel(String excelPath) {
        if (StrUtil.isNotEmpty(excelPath)) {
            ExcelReader reader = ExcelUtil.getReader(excelPath);
            List<Product> products = reader.readAll(Product.class);
            List<Integer> productIds = new LinkedList<>();
            for (Product pro : products) {
                productIds.add(pro.getId());
            }
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", productIds);
            List<Product> productList = this._selectList(queryWrapper);
            return  productList;
        }
        return null;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 9:41
     * @description 查询商品详细
     * @param id
     */
    @Override
    public ProductDetailDto queryProductDetail(Integer id) {
        ProductDetailDto pd = new ProductDetailDto();
        Product product = this._selectById(id);
        if (id == null) {
            throw new CorrectException("参数错误，id为空");
        }
        BeanUtil.copyProperties(product, pd);

        // 查询打折活动
        QueryWrapper<Discount> queryDiscountWrapper = new QueryWrapper<>();
        queryDiscountWrapper.eq("product_class_name", product.getProductClassName());
        queryDiscountWrapper.le("start_time", new Date());
        queryDiscountWrapper.ge("over_time",new Date());
        queryDiscountWrapper.eq("is_effective", 1);
        Discount discount = discountService._selectOne(queryDiscountWrapper);
        if (discount == null) {
            QueryWrapper<Discount> queryDiscountWrapper2 = new QueryWrapper<>();
            queryDiscountWrapper2.eq("product_class_name", "全场通用");
            queryDiscountWrapper2.le("start_time", new Date());
            queryDiscountWrapper2.ge("over_time",new Date());
            queryDiscountWrapper2.eq("is_effective", 1);
            Discount discount2 = discountService._selectOne(queryDiscountWrapper2);
            if (discount2 != null) {
                pd.setDiscountAmount(discount2.getDiscountAmount());
            }
        }else {
            pd.setDiscountAmount(discount.getDiscountAmount());
        }

        // 查询促销活动
        QueryWrapper<SinglePromotion> querySinglePromotionWrapper = new QueryWrapper<>();
        querySinglePromotionWrapper.eq("product_id", id);
        querySinglePromotionWrapper.le("start_time", new Date());
        querySinglePromotionWrapper.ge("over_time",new Date());
        querySinglePromotionWrapper.eq("is_effective", 1);
        SinglePromotion singlePromotion = singlePromotionService._selectOne(querySinglePromotionWrapper);
        if (singlePromotion != null) {
            pd.setSinglePromotion(singlePromotion);
        }

        // 查询是否有指定商品优惠券
        Coupon coupon = couponService.queryOneByProductId(id);
        pd.setCoupon(coupon);

        return pd;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 18:51
     * @description 根据客户名称获取客户的对比数据
     * @param customerName
     */
    @Override
    public List<Product> getProductCompareByCustomerId(String customerName) {
        if (StrUtil.isEmpty(customerName)) {
            throw new CorrectException("参数错误，顾客登录名称不可为空");
        }
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(2);
        Map<String, String> map = jedis.hgetAll(customerName);
        jedis.close();
        List<Product> list = new LinkedList<>();
        Set<String> keySet = map.keySet();
        for (String k : keySet) {
            String val = map.get(k);
            Product product = JSONUtil.toBean(JSONUtil.parseObj(val), Product.class);
            if (product != null) {
                list.add(product);
            }
        }

        return list;
    }
}