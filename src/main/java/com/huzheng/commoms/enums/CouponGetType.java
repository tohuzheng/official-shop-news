package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/4/11 13:35
 * @Description 优惠券获取类型1系统推送2商详页获取3密令获取'
 */
public enum CouponGetType {

    SYS_PUSH("系统推送",1),
    PRODUCT_DETAIL_GET("商详页获取",2),
    SECRET_GET("密令获取",3);

    CouponGetType(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    private String name;
    private Integer value;

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
