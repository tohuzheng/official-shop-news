package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单(BuyOrder)实体类
 *
 * @author zheng.hu
 * @since 2020-03-23 10:40:31
 */
@TableName("buy_order")
public class BuyOrder implements Serializable {
    private static final long serialVersionUID = -97437021723536219L;
    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 购买时间
    */
    private Date buyTime;
    /**
    * 支付总金额
    */
    private Double paySumMoney;
    /**
    * 支付状态,0-支付中,1-支付成功,2-支付失败
    */
    private Integer payStatus;
    /**
    * 此订单购买物品总数量
    */
    private Integer productSumNumber;
    /**
    * 收货地址id
    */
    private Integer addressId;
    /**
    * 订单状态,0未发货,1已发货,2已签收,3已退货
    */
    private Integer orderStatus;
    /**
    * 订单编号
    */
    private String orderCode;
    /**
    * 备注
    */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Double getPaySumMoney() {
        return paySumMoney;
    }

    public void setPaySumMoney(Double paySumMoney) {
        this.paySumMoney = paySumMoney;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getProductSumNumber() {
        return productSumNumber;
    }

    public void setProductSumNumber(Integer productSumNumber) {
        this.productSumNumber = productSumNumber;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}