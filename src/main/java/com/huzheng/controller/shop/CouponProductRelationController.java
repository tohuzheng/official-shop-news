package com.huzheng.controller.shop;

import com.huzheng.entity.CouponProductRelation;
import com.huzheng.service.ICouponProductRelationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (CouponProductRelation)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@RestController
@RequestMapping("couponProductRelation")
public class CouponProductRelationController {
    /**
     * 服务对象
     */
    @Autowired
    private ICouponProductRelationService couponProductRelationService;

}