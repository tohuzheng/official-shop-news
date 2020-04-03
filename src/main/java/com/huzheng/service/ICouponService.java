package com.huzheng.service;

import com.huzheng.entity.Coupon;
import com.huzheng.service.base.IBaseService;


/**
 * (Coupon)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponService extends IBaseService<Coupon> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    
}