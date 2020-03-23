package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.OrderStatus;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.service.IBuyOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 订单(BuyOrder)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-23 10:40:31
 */
@RestController
@RequestMapping("buyOrder")
public class BuyOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private IBuyOrderService buyOrderService;

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:03
     * @description 根据id删除记录
     */
    @PostMapping("/deleteOrder")
    public void deleteById(Integer id){
        buyOrderService.deleteById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:03
     * @description  分页查询，带条件
     */
    @PostMapping("/queryPage")
    public Page queryPage(QueryBuyOrderDto query, Date[] daterangeArr) {

        if (daterangeArr != null && daterangeArr.length == 2) {
            query.setStartTime(daterangeArr[0]);
            query.setOverTime(daterangeArr[1]);
        }

        return buyOrderService.queryPage(query);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:05
     * @description 改变订单状态
     */
    @PostMapping("/changeOrderStatus")
    public void changeOrderStatus(Integer status) {

    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:06
     * @description 添加订单
     */
    @PostMapping("/addBuyOrder")
    public void addBuyOrder() {

    }

    @PostMapping("/deliverGoods")
    public void deliverGoods(Integer id) {
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setId(id);
        buyOrder.setOrderStatus(OrderStatus.DELIVER.getValue());
        buyOrderService._updateById(buyOrder);
    }

}