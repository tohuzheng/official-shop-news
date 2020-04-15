package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Coupon)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponDao extends BaseMapper<Coupon> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param coupon 实例对象
     * @return 对象列表
     */
    List<Coupon> queryAll(Coupon coupon);

    /**
     * @author zheng.hu
     * @date 2020/4/11 9:47
     * @description 商详页指定产品优惠券，查询使用类型为指定产品的优惠卷
     * @param productId 产品id
     */
    Coupon queryOneProductCouponByProductId(Integer productId);
}