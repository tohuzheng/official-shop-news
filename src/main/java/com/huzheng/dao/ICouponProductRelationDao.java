package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.CouponProductRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (CouponProductRelation)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponProductRelationDao extends BaseMapper<CouponProductRelation> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param couponProductRelation 实例对象
     * @return 对象列表
     */
    List<CouponProductRelation> queryAll(CouponProductRelation couponProductRelation);


}