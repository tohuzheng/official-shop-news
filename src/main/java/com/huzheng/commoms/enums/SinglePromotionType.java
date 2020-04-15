package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/4/11 16:54
 * @Description
 */
public enum SinglePromotionType {

    SINGLE_REDUCE("单品直降",1),
    SINGLE_GIVES("单品赠品",2),
    N_M("n元m件",3);

    private String name;
    private Integer value;

    SinglePromotionType(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
