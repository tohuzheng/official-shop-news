package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.entity.BuyOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 订单(BuyOrder)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-03-23 10:40:31
 */
public interface IBuyOrderDao extends BaseMapper<BuyOrder> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param queryDto 实例对象
     * @return 对象列表
     */
    List<BuyOrderDto> queryAllPage(@Param("queryDto") QueryBuyOrderDto queryDto, @Param("offset") Integer offset,
                                   @Param("limit") Integer limit);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIdBuyOrder(Integer id);

}