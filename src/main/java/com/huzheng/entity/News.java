package com.huzheng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 胡正
 * @Date 2020/2/6 12:36
 * @Description 新闻表实体类
 */
public class News implements Serializable {
    private static final long serialVersionUID = 4960370575232928992L;

    private Integer id;
    private String title;
    private String newsDetail;
    private Integer likeNumber;
    private Integer type;
    private Date createTime;
    private String createName;

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

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
