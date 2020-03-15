package com.huzheng.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (News)实体类
 *
 * @author zheng.hu
 * @since 2020-03-11 20:59:47
 */
public class News implements Serializable {
    private static final long serialVersionUID = -94461564487027516L;
    
    private Integer id;
    /**
    * 新闻标题
    */
    private String title;
    /**
    * 新闻详细
    */
    private String detail;
    /**
    * 点赞数
    */
    private Integer readNumber;
    /**
    * 新闻类型,1行业动态2热点资讯3热点话题
    */
    private Integer type;
    /**
    * 新闻创建时间
    */
    private Date createDate;
    /**
    * 新闻编写者
    */
    private String createName;
    /**
    * 图片地址
    */
    private String imgUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}