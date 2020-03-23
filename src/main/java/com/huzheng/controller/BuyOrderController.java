package com.huzheng.controller;

import com.huzheng.entity.BuyOrder;
import com.huzheng.service.IBuyOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("/deleteOrder")
    public void deleteById(Integer id){
        buyOrderService.deleteById(id);
    }

}