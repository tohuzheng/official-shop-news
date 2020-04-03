package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Discount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 促销活动-打折(Discount)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-03 21:17:04
 */
public interface IDiscountDao extends BaseMapper<Discount> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param discount 实例对象
     * @return 对象列表
     */
    List<Discount> queryAll(Discount discount);


}