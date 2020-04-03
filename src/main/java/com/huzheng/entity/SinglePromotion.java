package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (SinglePromotion)实体类
 *
 * @author zheng.hu
 * @since 2020-04-03 17:44:38
 */
@TableName(value = "single_promotion")
public class SinglePromotion implements Serializable {
    private static final long serialVersionUID = 656691226175321907L;
    /**
    * 主键
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 促销名称
    */
    private String name;
    /**
    * 促销类型，1单品直降2单品赠品3N元M件
    */
    private Integer promotionType;
    /**
    * 参与活动的商品id
    */
    private Integer productId;
    /**
    * 赠品id
    */
    private Integer presenterId;
    /**
    * 直降类型的减多少钱
    */
    private BigDecimal reduceMoney;
    /**
    * N件商品
    */
    private Integer productNum;
    /**
    * M元
    */
    private Double sumMoney;
    /**
    * 活动开始时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;
    /**
    * 活动结束时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date overTime;
    /**
    * 创建活动时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createDate;
    /**
    * 是否生效，0未生效，1生效，2活动已结束
    */
    private Integer isEffective;


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

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(Integer presenterId) {
        this.presenterId = presenterId;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

}