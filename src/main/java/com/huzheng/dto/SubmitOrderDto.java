package com.huzheng.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/4/16 16:09
 * @Description
 */
public class SubmitOrderDto {
    /**
     * 每一件商品计算的结果
     */
    private List<OrderProductComputerDto> detailedList;
    /**
     * 收货地址id
     */
    private Integer addressId;
    /**
     * 优惠券id
     */
    private Integer couponId;
    /**
     * 订单总金额
     */
    private String amount;
    /**
     * 解密私钥的编号
     */
    private String keyNo;

    public List<OrderProductComputerDto> getDetailedList() {
        return detailedList;
    }

    public void setDetailedList(List<OrderProductComputerDto> detailedList) {
        this.detailedList = detailedList;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }
}
