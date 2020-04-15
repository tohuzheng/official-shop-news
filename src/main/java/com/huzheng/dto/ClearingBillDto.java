package com.huzheng.dto;

import com.huzheng.entity.Coupon;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/4/11 16:27
 * @Description 结算单dto
 */
public class ClearingBillDto {

    /**
     * 订单总金额
     */
    private BigDecimal orderSumMoney;

    /**
     * 可用优惠券
     */
    private List<Coupon> usableCoupon;

    /**
     * 每一件商品计算的结果
     */
    private List<OrderProductComputerDto> orderProductComputerDtos;

    public BigDecimal getOrderSumMoney() {
        return orderSumMoney;
    }

    public void setOrderSumMoney(BigDecimal orderSumMoney) {
        this.orderSumMoney = orderSumMoney;
    }

    public List<Coupon> getUsableCoupon() {
        return usableCoupon;
    }

    public void setUsableCoupon(List<Coupon> usableCoupon) {
        this.usableCoupon = usableCoupon;
    }

    public List<OrderProductComputerDto> getOrderProductComputerDtos() {
        return orderProductComputerDtos;
    }

    public void setOrderProductComputerDtos(List<OrderProductComputerDto> orderProductComputerDtos) {
        this.orderProductComputerDtos = orderProductComputerDtos;
    }
}
