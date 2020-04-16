package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (OrderDetail)实体类
 *
 * @author zheng.hu
 * @since 2020-03-23 10:51:22
 */
@TableName("order_detail")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -14079966127257548L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 关联订单id
    */
    private Integer orderId;
    /**
    * 商品id
    */
    private Integer productId;
    /**
    * 购买此商品数量
    */
    private Integer buyNumber;
    /**
    * 单价
    */
    private BigDecimal price;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 商品尺寸
    */
    private String productSize;
    /**
     *总金额
     */
    private BigDecimal sumAmount;
    /**
     *优惠金额
     */
    private BigDecimal reduceAmount;
    /**
     *赠品id
     */
    private Integer presenterId;
    /**
     *产品图片
     */
    private String productImgUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public Integer getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(Integer presenterId) {
        this.presenterId = presenterId;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}