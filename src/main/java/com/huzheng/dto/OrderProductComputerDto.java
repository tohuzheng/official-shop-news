package com.huzheng.dto;

import com.huzheng.entity.Product;

import java.math.BigDecimal;

/**
 * @Author 胡正
 * @Date 2020/4/11 16:33
 * @Description 计算每个订单产品的数据
 */
public class OrderProductComputerDto {
    /**
     * 主键id
     */
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
     * 合计金额
     */
    private BigDecimal sumMoney;
    /**
     * 优惠金额
     */
    private BigDecimal reduceMoney;
    /**
     * 赠品
     */
    private Product product;

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

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
