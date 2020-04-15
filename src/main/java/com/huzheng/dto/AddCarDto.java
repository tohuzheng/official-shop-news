package com.huzheng.dto;

import java.math.BigDecimal;

/**
 * @Author 胡正
 * @Date 2020/4/11 11:07
 * @Description 添加购物车dto
 */
public class AddCarDto {

    private Integer productId;
    private Integer promotionId;
    private Integer productCount;
    private BigDecimal discountAmount;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}
