package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/3/16 22:47
 * @Description
 */
public enum CustomerStatus {

    UNFREEZE("正常", 0), FREEZE("已冻结",1);

    private String name;
    private Integer value;

    CustomerStatus(String name,Integer value){
        this.name = name;
        this.value = value;
    }
}
