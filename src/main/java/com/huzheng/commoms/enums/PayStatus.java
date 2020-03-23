package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/3/15 11:35
 * @Description
 */
public enum PayStatus {

    PAYING("支付中",0),
    PAY_SUCCESS("支付成功",1),
    PAY_FAIL("支付失败",2);

    private String name;
    private Integer value;

    PayStatus(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
