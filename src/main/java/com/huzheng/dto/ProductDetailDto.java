package com.huzheng.dto;

import com.huzheng.entity.Coupon;
import com.huzheng.entity.SinglePromotion;
import java.math.BigDecimal;

/**
 * @Author 胡正
 * @Date 2020/4/7 23:31
 * @Description 查询产品详细dto
 */
public class ProductDetailDto {
    /**
     * 产品ID
     */
    private Integer id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品价格
     */
    private BigDecimal price;
    /**
     * 产品重量
     */
    private Double weight;
    /**
     * 产品尺寸
     */
    private String productSize;
    /**
     * 产品绑定类目名称
     */
    private String productClassName;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 库存数量
     */
    private Integer repertoryNumber;
    /**
     * 产地
     */
    private String originPlace;
    /**
     * 折扣数
     */
    private BigDecimal discountAmount;
    /**
     * 促销活动
     */
    private SinglePromotion singlePromotion;
    /**
     * 优惠券
     */
    private Coupon coupon;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getRepertoryNumber() {
        return repertoryNumber;
    }

    public void setRepertoryNumber(Integer repertoryNumber) {
        this.repertoryNumber = repertoryNumber;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public SinglePromotion getSinglePromotion() {
        return singlePromotion;
    }

    public void setSinglePromotion(SinglePromotion singlePromotion) {
        this.singlePromotion = singlePromotion;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
