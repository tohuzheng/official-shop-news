package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/3/15 11:35
 * @Description
 */
public enum  OrderStatus {

    UN_PAY("未支付",3);

    private String name;
    private Integer value;

    OrderStatus(String name,Integer value){
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
