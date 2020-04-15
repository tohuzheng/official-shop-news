package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 访客统计(VisitCount)实体类
 *
 * @author zheng.hu
 * @since 2020-04-07 15:23:42
 */
@TableName(value = "visit_count")
public class VisitCount implements Serializable {
    private static final long serialVersionUID = 906138405707972352L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 统计日期
    */
    private Object visitDate;
    /**
    * 访问量
    */
    private Integer viewCount;
    /**
    * 登录的人数
    */
    private Integer loginCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Object visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

}