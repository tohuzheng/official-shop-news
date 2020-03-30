package com.huzheng.commoms.websockets.dto;

import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/3/30 11:56
 * @Description 客服上线初始化消息dto
 */
public class SystemMessageDto {

    /**
     * 顾客名称
     */
    private String customerName;

    /**
     * 是否离线
     */
    private String offLine;
    /**
     * 消息体
     */
    private String msg;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOffLine() {
        return offLine;
    }

    public void setOffLine(String offLine) {
        this.offLine = offLine;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
