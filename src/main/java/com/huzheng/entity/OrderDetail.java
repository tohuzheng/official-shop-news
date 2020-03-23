package com.huzheng.entity;

import java.io.Serializable;

/**
 * (OrderDetail)实体类
 *
 * @author zheng.hu
 * @since 2020-03-23 10:51:22
 */
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -14079966127257548L;
    
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
    private Double price;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 商品尺寸
    */
    private String productSize;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

}