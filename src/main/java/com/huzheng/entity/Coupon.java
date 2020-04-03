package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;
import java.io.Serializable;

/**
 * (Coupon)实体类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@TableName(value = "coupon")
public class Coupon implements Serializable {
    private static final long serialVersionUID = -28482870222689436L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 优惠卷名称
    */
    private String name;
    /**
    * 发行数量
    */
    private Integer count;
    /**
    * 优惠卷面额
    */
    private Double amount;
    /**
    * 每人最多领取几张
    */
    private Integer perLimit;
    /**
    * 使用门槛，0表示无门槛
    */
    private Double minPoint;
    /**
    * 使用类型，0全场通用1指定分类2指定商品
    */
    private Integer useType;
    /**
    * 优惠卷有效时间类型,1相对时间,2绝对时间
    */
    private Integer effectiveTimeType;
    /**
    * 有效时间开始时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;
    /**
    * 有效时间结束时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date overTime;
    /**
    * 获取口令
    */
    private String code;
    /**
    * 有效天数
    */
    private Integer effectiveDay;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public Double getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(Double minPoint) {
        this.minPoint = minPoint;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getEffectiveTimeType() {
        return effectiveTimeType;
    }

    public void setEffectiveTimeType(Integer effectiveTimeType) {
        this.effectiveTimeType = effectiveTimeType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(Integer effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

}