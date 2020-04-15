package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/4/6 17:33
 * @Description 优惠券表中使用类型，0全场通用1指定分类2指定商品
 */
public enum UseTypeEnums {

    ALL_PRODUCT("全场通用", 0),
    RESTRICT_PRODUCT_CATEGORY("指定品类", 1),
    RESTRICT_PRODUCT("指定产品", 2);

    private String name;
    private Integer value;

    UseTypeEnums(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
