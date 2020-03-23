package com.huzheng.dto;

import com.huzheng.entity.BuyOrder;
import com.huzheng.entity.OrderDetail;

import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/3/23 11:07
 * @Description 订单dto
 */
public class BuyOrderDto extends BuyOrder {

    /**
     * 收件人姓名
     */
    private String receiverName;
    /**
     * 收件人电话
     */
    private String receiverTel;
    /**
     * 收件人地址
     */
    private String address;
    /**
     * 顾客id
     */
    private Integer customerId;
    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetails;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
