package com.huzheng.commoms.enums;

/**
 * @Author 胡正
 * @Date 2020/3/24 18:09
 * @Description 新闻资讯类型
 */
public enum NewsType {
    INDUSTRY_DYNAMICS("行业动态",1),
    HOT_NEWS("热点资讯",2),
    HOT_TOPIC("热点话题",3);

    private String name;
    private Integer value;

    NewsType(String name,Integer value){
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
