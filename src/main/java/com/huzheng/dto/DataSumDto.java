package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/4/7 14:58
 * @Description 总的数据统计
 */
public class DataSumDto {
    /**
     * 总用户数
     */
    private Integer sumUser;
    /**
     * 总订单数
     */
    private Integer sumOrder;
    /**
     * 总浏览量
     */
    private Integer sumView;

    public Integer getSumUser() {
        return sumUser;
    }

    public void setSumUser(Integer sumUser) {
        this.sumUser = sumUser;
    }

    public Integer getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(Integer sumOrder) {
        this.sumOrder = sumOrder;
    }

    public Integer getSumView() {
        return sumView;
    }

    public void setSumView(Integer sumView) {
        this.sumView = sumView;
    }
}
