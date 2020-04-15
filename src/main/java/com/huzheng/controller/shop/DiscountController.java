package com.huzheng.controller.shop;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Discount;
import com.huzheng.service.IDiscountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 促销活动-打折(Discount)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 21:17:04
 */
@RestController
@RequestMapping("discount")
public class DiscountController {
    /**
     * 服务对象
     */
    @Autowired
    private IDiscountService discountService;

    /**
     * @author zheng.hu
     * @date 2020/4/3 21:29
     * @description 分页查询
     * @param page
     * @param discount
     */
    @PostMapping("/queryDiscountPge")
    public Page<Discount> queryDiscountPge(Page page, Discount discount){
        return  discountService.queryPage(page,discount);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/4 22:19
     * @description 添加打折活动
     * @param discount
     */
    @PostMapping("/addDiscount")
    public void addDiscount(Discount discount){
        discount.setCreateDate(new Date());
        this.discountService._insert(discount);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/4 22:19
     * @description 更新打折活动
     * @param discount
     */
    @PostMapping("/updateDiscountInfo")
    public void updateDiscountInfo(Discount discount){
        if (discount.getId() == null) {
            throw new RuntimeException("id不可为空");
        }
        if (StrUtil.isEmpty(discount.getImgUrl())){
            discount.setImgUrl(null);
        }
        discountService._updateById(discount);
    }

}