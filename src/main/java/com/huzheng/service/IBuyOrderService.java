package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.dto.SubmitOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.service.base.IBaseService;

import java.util.Date;


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

    /**
     * @author zheng.hu
     * @date 2020/4/7 16:40
     * @description 查询某天的订单量
     * @param date
     */
    Integer queryOneDayOrderCount(Date date);

    /**
     * @author zheng.hu
     * @date 2020/4/15 14:37
     * @description 提交生成订单
     * @param submitOrderDto
     */
    void submitOrder(SubmitOrderDto submitOrderDto, String orderCode, String payAmount, Integer customerId);

    /**
     * @author zheng.hu
     * @date 2020/4/16 20:34
     * @description 根据订单号修改订单状态
     * @param orderCode
     */
    void changeOrderStatusByCode(String orderCode);
}