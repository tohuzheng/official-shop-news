package com.huzheng.commoms.websockets.dto;

/**
 * @Author 胡正
 * @Date 2020/3/30 16:43
 * @Description 客户发给客服人员的消息
 */
public class CustomerToAdmin {

    private String customerName;
    private String msg;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
