package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;
import java.io.Serializable;

/**
 * (OnlyCode)实体类
 *
 * @author zheng.hu
 * @since 2020-03-23 17:32:19
 */
@TableName(value = "only_code")
public class OnlyCode implements Serializable {
    private static final long serialVersionUID = 561409526372856936L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 商品id
    */
    private Integer productId;
    /**
    * 防伪码
    */
    private String uuid;
    /**
    * 生产日期
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date manufactureDate;


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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

}