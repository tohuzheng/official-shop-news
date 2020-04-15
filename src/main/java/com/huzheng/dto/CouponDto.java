package com.huzheng.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huzheng.entity.CouponProductRelation;
import com.huzheng.entity.Product;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/4/6 17:14
 * @Description 添加优惠券的dto
 */
public class CouponDto implements Serializable {

    private static final long serialVersionUID = 2036311457861674657L;

    private Integer id;
    /**
     * 优惠卷名称
     */
    private String name;
    /**
     * 发行数量
     */
    private Integer count;
    /**
     * 优惠卷面额
     */
    private BigDecimal amount;
    /**
     * 每人最多领取几张
     */
    private Integer perLimit;
    /**
     * 使用门槛，0表示无门槛
     */
    private BigDecimal minPoint;
    /**
     * 使用类型，0全场通用1指定分类2指定商品
     */
    private Integer useType;
    /**
     * 优惠卷有效时间类型,1相对时间,2绝对时间
     */
    private Integer effectiveTimeType;
    /**
     * 有效时间开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // jackson的注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // mvc的注解
    private Date startTime;
    /**
     * 有效时间结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date overTime;
    /**
     * 获取口令
     */
    private String code;
    /**
     * 有效天数
     */
    private Integer effectiveDay;

    /**
     * 优惠券指定产品品类
     */
    private String productClassName;
    /**
     * 优惠券指定产品
     */
    private List<CouponProductRelation> couponProducts;

    /**
     * 是否生效，0不生效，1生效
     */
    private Integer isEffective;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public BigDecimal getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getEffectiveTimeType() {
        return effectiveTimeType;
    }

    public void setEffectiveTimeType(Integer effectiveTimeType) {
        this.effectiveTimeType = effectiveTimeType;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(Integer effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    public List<CouponProductRelation> getCouponProducts() {
        return couponProducts;
    }

    public void setCouponProducts(List<CouponProductRelation> couponProducts) {
        this.couponProducts = couponProducts;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }
}
