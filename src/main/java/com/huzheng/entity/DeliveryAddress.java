package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 收货地址表(DeliveryAddress)实体类
 *
 * @author zheng.hu
 * @since 2020-04-15 22:07:35
 */
@TableName(value = "delivery_address")
public class DeliveryAddress implements Serializable {
    private static final long serialVersionUID = -19283742691168452L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 收件人姓名
    */
    private String receiverName;
    /**
    * 收件地址
    */
    private String address;
    /**
    * 收件人电话
    */
    private String receiverTel;
    /**
    * 顾客id
    */
    private Integer customerId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}