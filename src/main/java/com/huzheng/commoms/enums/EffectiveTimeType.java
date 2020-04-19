package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/4/19 23:03
 * @Description
 */
public enum  EffectiveTimeType {

    RELATIVE_TIME("相对时间",1),
    ABS_TIME("绝对时间",2);

    private String name;
    private Integer value;

    EffectiveTimeType(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }}
