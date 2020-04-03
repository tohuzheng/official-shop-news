package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.entity.Product;
import com.huzheng.dao.IProductDao;
import com.huzheng.service.IProductService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}