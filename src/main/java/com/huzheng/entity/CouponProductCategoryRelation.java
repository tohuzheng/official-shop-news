package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (CouponProductCategoryRelation)实体类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@TableName(value = "coupon_product_category_relation")
public class CouponProductCategoryRelation implements Serializable {
    private static final long serialVersionUID = -41311110623720520L;
    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 优惠卷id
    */
    private Integer couponId;
    /**
    * 使用品类名称
    */
    private String productClassName;


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

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

}