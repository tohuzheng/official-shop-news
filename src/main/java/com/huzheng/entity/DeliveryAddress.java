package com.huzheng.entity;

import java.io.Serializable;

/**
 * 收货地址表(DeliveryAddress)实体类
 *
 * @author zheng.hu
 * @since 2020-03-23 10:52:12
 */
public class DeliveryAddress implements Serializable {
    private static final long serialVersionUID = 146653516448472266L;
    
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
    private String tel;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}