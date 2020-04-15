package com.huzheng.controller.shop;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.CouponGetType;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.CouponGetList;
import com.huzheng.entity.Customer;
import com.huzheng.service.ICouponGetListService;
import com.huzheng.service.ICouponService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * (CouponGetList)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
@RestController
@RequestMapping("couponGetList")
public class CouponGetListController {
    /**
     * 服务对象
     */
    @Autowired
    private ICouponGetListService couponGetListService;
    @Autowired
    private ICouponService couponService;

    /**
     * @author zheng.hu
     * @date 2020/4/7 11:58
     * @description 分页查询
     * @param page
     * @param couponGetList
     */
    @PostMapping("queryCouponGetListPage")
    public Page<CouponGetList> queryCouponGetListPage(Page page, CouponGetList couponGetList) {

        return couponGetListService.queryPage(page, couponGetList);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 13:28
     * @description 领取优惠券
     * @param couponId,getType
     */
    @RequestMapping("/userGetCoupon")
    public String userGetCoupon(Integer couponId,Integer getType, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer userInfo = (Customer) session.getAttribute("userInfo");
        Coupon coupon = couponService._selectById(couponId);
        if (coupon.getStartTime().getTime()< System.currentTimeMillis() &&
                System.currentTimeMillis() < coupon.getOverTime().getTime()) {
            CouponGetList couponGetList = new CouponGetList();
            couponGetList.setCustomerId(userInfo.getId());
            couponGetList.setCustomerName(userInfo.getUsername());
            couponGetList.setCouponId(couponId);
            couponGetList.setCreateDate(new Date());
            couponGetList.setGetType(getType);
            couponGetList.setUseStatus(0);
            if (coupon.getEffectiveTimeType().equals(1)) {
                Date start = new Date();
                Date over = DateUtil.offsetDay(start, coupon.getEffectiveDay());
                couponGetList.setEffectiveTimeStart(start);
                couponGetList.setEffectiveTimeOver(over);
            }else {
                couponGetList.setEffectiveTimeStart(coupon.getStartTime());
                couponGetList.setEffectiveTimeOver(coupon.getOverTime());
            }
            couponGetListService._insert(couponGetList);
            return "领取成功";
        }else {
            return "该优惠券活动已结束，不可领取";
        }

    }

}