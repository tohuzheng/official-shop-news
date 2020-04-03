package com.huzheng.controller.shop;

import com.huzheng.entity.Coupon;
import com.huzheng.service.ICouponService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Coupon)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@RestController
@RequestMapping("coupon")
public class CouponController {
    /**
     * 服务对象
     */
    @Autowired
    private ICouponService couponService;


}