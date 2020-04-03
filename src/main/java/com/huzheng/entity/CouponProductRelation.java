package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (CouponProductRelation)实体类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@TableName(value = "coupon_product_relation")
public class CouponProductRelation implements Serializable {
    private static final long serialVersionUID = -31572239220575333L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 优惠卷id
     */
    private Integer couponId;
    /**
     * 产品名称
     */
    private String productName;

    /**
     *产品id
     */
    private Integer productId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}