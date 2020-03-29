package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;
import java.io.Serializable;

/**
 * (Suggest)实体类
 *
 * @author zheng.hu
 * @since 2020-03-29 17:06:38
 */
@TableName(value = "suggest")
public class Suggest implements Serializable {
    private static final long serialVersionUID = -56638557300346845L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 意见内容
    */
    private String suggestContent;
    /**
    * 提建议的时间
    */
    @Column(name = "create_date")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}