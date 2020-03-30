package com.huzheng.commoms.websockets.dto;

/**
 * @Author 胡正
 * @Date 2020/3/30 13:53
 * @Description 管理员发给服务器的数据模型
 */
public class ReceiptAdminMsgDto {
    private String toCustomerName;
    private String msg;

    public String getToCustomerName() {
        return toCustomerName;
    }

    public void setToCustomerName(String toCustomerName) {
        this.toCustomerName = toCustomerName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
