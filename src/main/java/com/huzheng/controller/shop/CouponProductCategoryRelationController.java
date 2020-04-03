package com.huzheng.controller.shop;

import com.huzheng.entity.CouponProductCategoryRelation;
import com.huzheng.service.ICouponProductCategoryRelationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (CouponProductCategoryRelation)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@RestController
@RequestMapping("couponProductCategoryRelation")
public class CouponProductCategoryRelationController {
    /**
     * 服务对象
     */
    @Autowired
    private ICouponProductCategoryRelationService couponProductCategoryRelationService;



}