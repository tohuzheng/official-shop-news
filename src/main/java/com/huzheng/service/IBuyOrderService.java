package com.huzheng.service;

import com.huzheng.entity.BuyOrder;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 订单(BuyOrder)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-23 10:40:31
 */
public interface IBuyOrderService extends IBaseService<BuyOrder> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    
}