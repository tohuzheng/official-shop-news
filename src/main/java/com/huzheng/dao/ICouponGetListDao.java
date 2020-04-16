package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.CouponGetList;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (CouponGetList)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
public interface ICouponGetListDao extends BaseMapper<CouponGetList> {

    /**
     * @author zheng.hu
     * @date 2020/4/16 11:35
     * @description 根据获取id查询优惠券活动信息
     * @param id
     */
    Coupon queryOneCouponById(Integer id);

}