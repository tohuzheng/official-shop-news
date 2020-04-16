package com.huzheng.service;

import com.huzheng.entity.CouponProductRelation;
import com.huzheng.service.base.IBaseService;


/**
 * (CouponProductRelation)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponProductRelationService extends IBaseService<CouponProductRelation> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/4/16 14:35
     * @description 根据优惠券id查询优惠券
     * @param couponId
     */
    CouponProductRelation queryOneByCouponId(Integer couponId);
}