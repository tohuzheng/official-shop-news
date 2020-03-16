package com.huzheng.dto;

import java.util.Date;

/**
 * @Author 胡正
 * @Date 2020/3/16 18:07
 * @Description
 */
public class QueryCustomerDto {

    private String name;
    private String email;
    private String tel;
    private Date[] daterangeArr;
    private String username;
    private int pageSize;
    private int pageNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date[] getDaterangeArr() {
        return daterangeArr;
    }

    public void setDaterangeArr(Date[] daterangeArr) {
        this.daterangeArr = daterangeArr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
