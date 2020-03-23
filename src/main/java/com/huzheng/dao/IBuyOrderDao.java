package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BuyOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BuyOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param buyOrder 实例对象
     * @return 对象列表
     */
    List<BuyOrder> queryAll(BuyOrder buyOrder);

    /**
     * 新增数据
     *
     * @param buyOrder 实例对象
     * @return 影响行数
     */
    int insertBuyOrder(BuyOrder buyOrder);

    /**
     * 修改数据
     *
     * @param buyOrder 实例对象
     * @return 影响行数
     */
    int updateBuyOrder(BuyOrder buyOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIdBuyOrder(Integer id);

}