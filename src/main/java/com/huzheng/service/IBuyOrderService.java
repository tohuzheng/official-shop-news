package com.huzheng.service;

import com.huzheng.entity.BuyOrder;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 订单(BuyOrder)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-14 23:51:33
 */
public interface IBuyOrderService extends IBaseService<BuyOrder> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BuyOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BuyOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param buyOrder 实例对象
     * @return 实例对象
     */
    BuyOrder insert(BuyOrder buyOrder);

    /**
     * 修改数据
     *
     * @param buyOrder 实例对象
     * @return 实例对象
     */
    BuyOrder update(BuyOrder buyOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    
     /**
     * 通过实体作为where条件查询
     *
     * @param buyOrder 条件
     * @return 查询结果集合
     */
    List<BuyOrder> queryAllByCondition(BuyOrder buyOrder);

}