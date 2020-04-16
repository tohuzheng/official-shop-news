package com.huzheng.dto;

import java.math.BigDecimal;

/**
 * @Author 胡正
 * @Date 2020/4/11 16:33
 * @Description 计算每个订单产品的数据
 */
public class OrderProductComputerDto {
    /**
     * 购物车id
     */
    private Integer buycarId;
    /**
     * 商品表id
     */
    private Integer productId;
    /**
     * 此商品数量
     */
    private Integer productNumber;
    /**
     * 产品图片
     */
    private String imgurl;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类目
     */
    private String productClassName;
    /**
     * 产品规格
     */
    private String productSize;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 促销活动id
     */
    private Integer singlePromotionId;
    /**
     * 促销活动名称
     */
    private Integer singlePromotionName;
    /**
     * 折扣数
     */
    private BigDecimal discountAmount;
    /**
     * 折扣活动id
     */
    private Integer discountId;
    /**
     * 合计金额
     */
    private BigDecimal sumMoney;
    /**
     * 优惠金额
     */
    private BigDecimal reduceMoney;
    /**
     * 赠品id
     */
    private Integer presenterId;
    /**
     * 赠品名称
     */
    private String presenterName;

    public Integer getBuycarId() {
        return buycarId;
    }

    public void setBuycarId(Integer buycarId) {
        this.buycarId = buycarId;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public Integer getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(Integer presenterId) {
        this.presenterId = presenterId;
    }

    public Integer getSinglePromotionName() {
        return singlePromotionName;
    }

    public void setSinglePromotionName(Integer singlePromotionName) {
        this.singlePromotionName = singlePromotionName;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }
}
