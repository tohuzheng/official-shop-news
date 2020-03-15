package com.huzheng.entity;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author zheng.hu
 * @since 2020-03-11 21:24:11
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 368187388991993658L;
    
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
    private String size;
    /**
    * 产品绑定类目id
    */
    private Integer productClassId;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(Integer productClassId) {
        this.productClassId = productClassId;
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

}