package com.huzheng.dto;

import com.huzheng.entity.Coupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/4/11 16:27
 * @Description 确认订单dto
 */
public class ConfirmOrderDto {

    /**
     * 订单总金额
     */
    private BigDecimal orderSumMoney;
    /**
     * 优惠总金额
     */
    private BigDecimal reduceSumMoney;

    /**
     * 可用优惠券
     */
    private Set<Coupon> usableCoupon;

    /**
     * 每一件商品计算的结果
     */
    private List<OrderProductComputerDto> orderProductComputerDtos;

    public BigDecimal getOrderSumMoney() {
        return orderSumMoney;
    }

    public BigDecimal getReduceSumMoney() {
        return reduceSumMoney;
    }

    public void setReduceSumMoney(BigDecimal reduceSumMoney) {
        this.reduceSumMoney = reduceSumMoney;
    }

    public void setOrderSumMoney(BigDecimal orderSumMoney) {
        this.orderSumMoney = orderSumMoney;
    }

    public Set<Coupon> getUsableCoupon() {
        return usableCoupon;
    }

    public void setUsableCoupon(Set<Coupon> usableCoupon) {
        this.usableCoupon = usableCoupon;
    }

    public List<OrderProductComputerDto> getOrderProductComputerDtos() {
        return orderProductComputerDtos;
    }

    public void setOrderProductComputerDtos(List<OrderProductComputerDto> orderProductComputerDtos) {
        this.orderProductComputerDtos = orderProductComputerDtos;
    }
}
