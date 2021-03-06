package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.OrderStatus;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.entity.Customer;
import com.huzheng.service.IBuyOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
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
     * @description  分页查询带条件
     */
    @PostMapping("/queryPage")
    public Page queryPage(QueryBuyOrderDto query, Date[] daterangeArr, HttpServletRequest request) {

        if (daterangeArr != null && daterangeArr.length == 2) {
            query.setStartTime(daterangeArr[0]);
            query.setOverTime(daterangeArr[1]);
        }

        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo instanceof Customer) {
            query.setCustomerId(((Customer) userInfo).getId());
        }

        return buyOrderService.queryPage(query);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:05
     * @description 改变订单状态
     */
    @PostMapping("/changeOrderStatus")
    public void changeOrderStatus(Integer status,Integer id) {
        if (id != null && status != null) {
            BuyOrder buyOrder =new BuyOrder();
            buyOrder.setId(id);
            buyOrder.setOrderStatus(status);
            this.buyOrderService._updateById(buyOrder);
        }else {
            throw new RuntimeException("参数异常无法更新");
        }

    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:06
     * @description 提交生成订单
     */
    @PostMapping("/submitOrder")
    public void submitOrder(Integer[] buycarIds) {

    }

    /**
     * @author zheng.hu
     * @date 2020/3/24 15:37
     * @description 发货
     */
    @PostMapping("/deliverGoods")
    public void deliverGoods(Integer id) {
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setId(id);
        buyOrder.setOrderStatus(OrderStatus.DELIVER.getValue());
        buyOrderService._updateById(buyOrder);
    }

}