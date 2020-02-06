package com.huzheng.entity;


import java.io.Serializable;

/**
 * @Author 胡正
 * @Date 2020/2/6 12:14
 * @Description 登录表实体类
 */
public class Login implements Serializable {

    private static final long serialVersionUID = -4067027112912907493L;

    private Integer id;
    private String username;
    private String password;
    private String role;  //角色
    private Integer consumerId; //顾客表id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

}
