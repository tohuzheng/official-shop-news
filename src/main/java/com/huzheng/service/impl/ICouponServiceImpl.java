package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.UseTypeEnums;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.dao.ICouponDao;
import com.huzheng.dto.CouponDto;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.CouponProductCategoryRelation;
import com.huzheng.entity.CouponProductRelation;
import com.huzheng.entity.Product;
import com.huzheng.service.ICouponProductCategoryRelationService;
import com.huzheng.service.ICouponProductRelationService;
import com.huzheng.service.ICouponService;
import com.huzheng.service.IProductService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * (Coupon)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@Service("couponService")
public class ICouponServiceImpl extends IBaseServiceImpl<ICouponDao, Coupon> implements ICouponService {

    @Autowired
    private ICouponDao couponDao;

    @Autowired
    private ICouponProductCategoryRelationService categoryRelationService;

    @Autowired
    private ICouponProductRelationService productRelationService;

    @Autowired
    private IProductService productService;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        Coupon coupon = this._selectById(id);
        if (UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue().equals(coupon.getUseType())) {
            // 指定品类
            QueryWrapper<CouponProductCategoryRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("coupon_id", id);
            categoryRelationService._delete(wrapper);
        }

        if (UseTypeEnums.RESTRICT_PRODUCT.getValue().equals(coupon.getUseType())) {
            // 指定商品
            QueryWrapper<CouponProductRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("coupon_id", id);
            productRelationService._delete(queryWrapper);
        }

        return this._deleteById(id)>0;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/6 17:31
     * @description 添加优惠券
     * @param couponDto
     */
    @Transactional
    @Override
    public void addCoupon(CouponDto couponDto) {

        Coupon coupon = new Coupon();
        BeanUtil.copyProperties(couponDto, coupon);
        this._insert(coupon);

        if (UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue().equals(couponDto.getUseType())) {
            // 指定品类
            CouponProductCategoryRelation categoryRelation = new CouponProductCategoryRelation();
            categoryRelation.setCouponId(coupon.getId());
            categoryRelation.setProductClassName(couponDto.getProductClassName());
            categoryRelationService._insert(categoryRelation);
        }

        if (UseTypeEnums.RESTRICT_PRODUCT.getValue().equals(couponDto.getUseType())) {
            // 指定商品
            for (CouponProductRelation product : couponDto.getCouponProducts()) {
                CouponProductRelation productRelation = new CouponProductRelation();
                productRelation.setCouponId(coupon.getId());
                productRelation.setProductId(product.getId());
                Product p = productService._selectById(product.getId());
                productRelation.setProductName(p.getName());
                productRelationService._insert(productRelation);
            }

        }
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 9:18
     * @description 分页查询
     * @param page
     * @param coupon
     */
    @Override
    public Page queryPage(Page page, Coupon coupon) {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(coupon);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);
        Page iPage = (Page) this._selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 10:00
     * @description 查看优惠券详细
     * @param id
     */
    @Override
    public CouponDto queryDetail(Integer id) {
        if (id == null) {
            throw new RuntimeException("id参数不可为空");
        }
        CouponDto couponDto = new CouponDto();
        Coupon coupon = this._selectById(id);
        BeanUtil.copyProperties(coupon, couponDto);

        if (UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue().equals(coupon.getUseType())){
            // 指定品类
            QueryWrapper<CouponProductCategoryRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("coupon_id", id);
            CouponProductCategoryRelation categoryRelation = categoryRelationService._selectOne(queryWrapper);
            couponDto.setProductClassName(categoryRelation.getProductClassName());
        }

        if (UseTypeEnums.RESTRICT_PRODUCT.getValue().equals(coupon.getUseType())) {
            // 指定商品
            QueryWrapper<CouponProductRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("coupon_id", id);
            List<CouponProductRelation> couponProductRelationList = productRelationService._selectList(queryWrapper);
            couponDto.setCouponProducts(couponProductRelationList);
        }

        return couponDto;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 10:07
     * @description 根据产品id查询是否有优惠券
     * @param productId
     */
    @Override
    public Coupon queryOneByProductId(Integer productId) {
        Coupon coupon = couponDao.queryOneProductCouponByProductId(productId);
        return coupon;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 18:29
     * @description 根据优惠券和产品查询优惠券
     * @param id
     * @param product
     */
    @Override
    public Coupon queryOneById(Integer id, Product product) {
        Coupon coupon = this._selectById(id);
        Integer useType = coupon.getUseType();

        if (UseTypeEnums.ALL_PRODUCT.getValue().equals(useType)) {
            // 全场通用
            return coupon;
        }

        if (UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue().equals(useType)) {
            // 指定分类
            CouponDto couponDto = this.queryDetail(id);
            if (couponDto.getProductClassName().equals(product.getProductClassName())) {
                return coupon;
            }
        }

        if (UseTypeEnums.RESTRICT_PRODUCT.getValue().equals(useType)) {
            // 指定产品
            CouponDto couponDto = this.queryDetail(id);
            List<CouponProductRelation> couponProducts = couponDto.getCouponProducts();
            for (CouponProductRelation relation:couponProducts) {
                if (relation.getCouponId().equals(id)) {
                    return coupon;
                }
            }
        }

        return null;
    }
}