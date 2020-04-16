package com.huzheng.service;

import com.huzheng.entity.CouponProductCategoryRelation;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (CouponProductCategoryRelation)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponProductCategoryRelationService extends IBaseService<CouponProductCategoryRelation> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    CouponProductCategoryRelation queryOneByCouponId(Integer couponId);
    
}