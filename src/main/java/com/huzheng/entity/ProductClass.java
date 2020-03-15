package com.huzheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 产品类目(ProductClass)实体类
 *
 * @author zheng.hu
 * @since 2020-03-13 23:48:22
 */
@TableName("product_class")
public class ProductClass implements Serializable {
    private static final long serialVersionUID = 893012026714242408L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 类目名
    */
    private String className;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}