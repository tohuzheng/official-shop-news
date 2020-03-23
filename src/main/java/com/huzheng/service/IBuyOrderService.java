package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.service.base.IBaseService;


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

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:20
     * @description 分页查询
     */
    Page<BuyOrderDto> queryPage(QueryBuyOrderDto queryDto);


    
}