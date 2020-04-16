package com.huzheng.dto;

import java.util.Date;

/**
 * @Author 胡正
 * @Date 2020/3/23 11:14
 * @Description
 */
public class QueryBuyOrderDto {
    /**
     * 订单购买开始时间
     */
    private Date startTime;
    /**
     * 订单购买结束时间
     */
    private Date overTime;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 收件人姓名
     */
    private String receiverName;
    /**
     * 收件人电话
     */
    private String receiverTel;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 顾客id
     */
    private Integer customerId;
    /**
     * 当前页码
     */
    private Integer current=1;
    /**
     * 一页的大小
     */
    private Integer size=10;

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

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
