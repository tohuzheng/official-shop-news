package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/3/23 10:31
 * @Description 订单状态枚举类：0-未发货，1-已发货，2-已签收，3-已退货
 */
public enum OrderStatus {

    UN_DELIVER("未发货",0),
    DELIVER("已发货", 1),
    RECEIVED("已签收",2),
    SALES_RETURN("已退货",3);

    private String name;
    private Integer value;

    OrderStatus(String name, Integer value){
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
