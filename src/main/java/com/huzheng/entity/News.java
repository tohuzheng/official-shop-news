package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * (News)实体类
 *
 * @author zheng.hu
 * @since 2020-03-24 18:14:31
 */
@TableName("news")
public class News implements Serializable {
    private static final long serialVersionUID = 868908355862955894L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 新闻标题
    */
    private String title;
    /**
    * 新闻详细html格式
    */
    private String detailHtml;
    /**
    * 点赞数
    */
    private Integer readNumber;
    /**
    * 新闻类型,1行业动态2热点资讯3热点话题
    */
    private Integer newsType;
    /**
    * 新闻创建时间
    */
    private Date createDate;
    /**
    * 新闻编写者
    */
    private String newsAuthor;
    /**
    * 新闻详细markdown格式
    */
    private String detailMarkdown;


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

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getDetailMarkdown() {
        return detailMarkdown;
    }

    public void setDetailMarkdown(String detailMarkdown) {
        this.detailMarkdown = detailMarkdown;
    }

}