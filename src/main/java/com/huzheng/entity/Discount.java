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
 * 促销活动-打折(Discount)实体类
 *
 * @author zheng.hu
 * @since 2020-04-03 21:17:04
 */
@TableName(value = "discount")
public class Discount implements Serializable {
    private static final long serialVersionUID = 765733794696338669L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 打折活动标题
    */
    private String title;
    /**
    * 活动针对类目，全场通用或者指定类目名称
    */
    private String productClassName;
    /**
    * 折扣数
    */
    private BigDecimal discountAmount;
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
    * 活动banner图
    */
    private String imgUrl;
    /**
    * 是否推入首页banner，0不推入1推入
    */
    private Integer isPushHomeBanner;
    /**
    * 是否推入品类banner，0不推入，1推入
    */
    private Integer isPushCategoryBanner;
    /**
    * 活动创建时间
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsPushHomeBanner() {
        return isPushHomeBanner;
    }

    public void setIsPushHomeBanner(Integer isPushHomeBanner) {
        this.isPushHomeBanner = isPushHomeBanner;
    }

    public Integer getIsPushCategoryBanner() {
        return isPushCategoryBanner;
    }

    public void setIsPushCategoryBanner(Integer isPushCategoryBanner) {
        this.isPushCategoryBanner = isPushCategoryBanner;
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