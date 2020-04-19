package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.EffectiveTimeType;
import com.huzheng.commoms.enums.UseTypeEnums;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.CouponGetList;
import com.huzheng.dao.ICouponGetListDao;
import com.huzheng.entity.Customer;
import com.huzheng.service.ICouponGetListService;
import com.huzheng.service.ICouponService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (CouponGetList)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
@Service("couponGetListService")
public class ICouponGetListServiceImpl extends IBaseServiceImpl<ICouponGetListDao, CouponGetList> implements ICouponGetListService {
    @Autowired
    private ICouponGetListDao couponGetListDao;
    @Autowired
    private ICouponService couponService;
    /**
     * @author zheng.hu
     * @date 2020/4/7 11:54
     * @description 分页查询
     * @param page
     * @param couponGetList
     */
    @Override
    public Page<CouponGetList> queryPage(Page page, CouponGetList couponGetList) {
        QueryWrapper<CouponGetList> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(couponGetList);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);
        Page iPage = (Page) this._selectPage(page, queryWrapper);

        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/16 21:04
     * @description 根据顾客id查询他持有的所有没使用的有效优惠券
     * @param customerId
     */
    @Override
    public List<CouponGetList> queryListByCustomerId(Integer customerId) {
        QueryWrapper<CouponGetList> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("effective_time_start", new Date());
        queryWrapper.ge("effective_time_over", new Date());
        queryWrapper.eq("customer_id", customerId);
        queryWrapper.eq("use_status", 0);
        List<CouponGetList> couponGetLists = this._selectList(queryWrapper);
        return couponGetLists;
    }

    @Override
    public Coupon queryOneCouponById(Integer id) {
        if (id == null) {
            throw new CorrectException("参数错误id为空");
        }
        return couponGetListDao.queryOneCouponById(id);
    }

    @Override
    public void changeUseStatusByCustomerIdAndCouponId(Integer customerId, Integer couponId) {
        CouponGetList couponGetList = new CouponGetList();
        couponGetList.setUseStatus(1);
        couponGetList.setUseTime(new Date());
        QueryWrapper<CouponGetList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id",couponId);
        queryWrapper.eq("customer_id", customerId);
        this._update(couponGetList,queryWrapper);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 22:48
     * @description 根据顾客id查寻所有领取的优惠券信息
     * @param customerId
     */
    @Override
    public List<Coupon> queryCouponByCustomerId(Integer customerId) {
        List<CouponGetList> couponGetLists = this.queryListByCustomerId(customerId);
        List<Integer> ids = new ArrayList<>();
        for (CouponGetList couponGetList: couponGetLists) {
            ids.add(couponGetList.getCouponId());
        }
        if (ids.size() == 0) {
            return null;
        }
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Coupon> couponList = couponService._selectList(queryWrapper);
        return couponList;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 22:52
     * @description 密令领取优惠券
     * @param code
     * @param customer
     */
    @Override
    public void requireCoupon(String code, Customer customer) {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        queryWrapper.ge("over_time", new Date());
        queryWrapper.le("start_time", new Date());
        queryWrapper.eq("is_effective", 1);
        queryWrapper.ge("count",1);
        Coupon coupon = couponService._selectOne(queryWrapper);
        if (coupon == null) {
            throw new CorrectException("领取失败，数量已完或活动已过期");
        }else {
            CouponGetList couponGetList = new CouponGetList();
            couponGetList.setUseStatus(0);
            couponGetList.setGetType(3);
            couponGetList.setCreateDate(new Date());
            couponGetList.setCustomerName(customer.getUsername());
            couponGetList.setCustomerId(customer.getId());
            couponGetList.setCouponId(coupon.getId());
            if (EffectiveTimeType.ABS_TIME.getValue().equals(coupon.getEffectiveTimeType())) {
                // 绝对时间
                couponGetList.setEffectiveTimeOver(coupon.getOverTime());
                couponGetList.setEffectiveTimeStart(coupon.getStartTime());
            }else {
                // 相对时间
                couponGetList.setEffectiveTimeStart(new Date());
                couponGetList.setEffectiveTimeOver(DateUtil.offsetDay(new Date(), coupon.getEffectiveDay()));
            }

            this._insert(couponGetList);
        }
    }
}