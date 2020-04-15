package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/4/7 14:57
 * @Description 今日数据统计
 */
public class DataTodayDto {
    /**
     * 新增用户
     */
    private Integer todayNewUser;
    /**
     * 新增订单
     */
    private Integer todayNewOrder;
    /**
     * 今日浏览量
     */
    private Integer todayView;

    public Integer getTodayNewUser() {
        return todayNewUser;
    }

    public void setTodayNewUser(Integer todayNewUser) {
        this.todayNewUser = todayNewUser;
    }

    public Integer getTodayNewOrder() {
        return todayNewOrder;
    }

    public void setTodayNewOrder(Integer todayNewOrder) {
        this.todayNewOrder = todayNewOrder;
    }

    public Integer getTodayView() {
        return todayView;
    }

    public void setTodayView(Integer todayView) {
        this.todayView = todayView;
    }
}
