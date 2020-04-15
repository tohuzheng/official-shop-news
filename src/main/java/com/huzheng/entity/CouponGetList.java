package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;
import java.io.Serializable;

/**
 * (CouponGetList)实体类
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
@TableName(value = "coupon_get_list")
public class CouponGetList implements Serializable {
    private static final long serialVersionUID = -99956262534053940L;
    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 优惠卷id
    */
    private Integer couponId;
    /**
    * 顾客id
    */
    private Integer customerId;
    /**
    * 顾客名称
    */
    private String customerName;
    /**
    * 订单id
    */
    private Integer orderId;
    /**
    * 获取类型：1系统推送2商详页获取3密令获取
    */
    private Integer getType;
    /**
    * 获取时间
    */
    @Column(name = "use_time")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createDate;
    /**
    * 使用时间
    */
    @Column(name = "use_time")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date useTime;
    /**
    * 使用状态
    */
    private Integer useStatus;
    /**
    * 有效开始时间
    */
    @Column(name = "effective_time_start")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date effectiveTimeStart;
    /**
    * 有效结束时间
    */
    @Column(name = "effective_time_over")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date effectiveTimeOver;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGetType() {
        return getType;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Date getEffectiveTimeStart() {
        return effectiveTimeStart;
    }

    public void setEffectiveTimeStart(Date effectiveTimeStart) {
        this.effectiveTimeStart = effectiveTimeStart;
    }

    public Date getEffectiveTimeOver() {
        return effectiveTimeOver;
    }

    public void setEffectiveTimeOver(Date effectiveTimeOver) {
        this.effectiveTimeOver = effectiveTimeOver;
    }

}