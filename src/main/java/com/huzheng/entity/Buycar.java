package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车(Buycar)实体类
 *
 * @author zheng.hu
 * @since 2020-04-11 11:05:25
 */
@TableName("buycar")
public class Buycar implements Serializable {
    private static final long serialVersionUID = -53523216133877161L;
    /**
    * 主键id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 商品表id
    */
    private Integer productId;
    /**
    * 此商品数量
    */
    private Integer productNumber;
    /**
    * 顾客表id
    */
    private Integer customerId;
    /**
    * 促销活动id
    */
    private Integer singlePromotionId;
    /**
    * 折扣数
    */
    private BigDecimal discountAmount;
    /**
    * 图片
    */
    private String productImgurl;
    /**
    * 名称
    */
    private String productName;
    /**
    * 规格
    */
    private String productSize;
    /**
    * 单价
    */
    private BigDecimal price;
    /**
     * 类目名称
     */
    private String productClassName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSinglePromotionId() {
        return singlePromotionId;
    }

    public void setSinglePromotionId(Integer singlePromotionId) {
        this.singlePromotionId = singlePromotionId;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getProductImgurl() {
        return productImgurl;
    }

    public void setProductImgurl(String productImgurl) {
        this.productImgurl = productImgurl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }
}