package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.CouponGetList;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (CouponGetList)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
public interface ICouponGetListService extends IBaseService<CouponGetList> {

    Page<CouponGetList> queryPage(Page page, CouponGetList couponGetList);

    List<CouponGetList> queryListByCustomerId(Integer customerId);

    Coupon queryOneCouponById(Integer id);

    void changeUseStatusByCustomerIdAndCouponId(Integer customerId,Integer couponId);
}