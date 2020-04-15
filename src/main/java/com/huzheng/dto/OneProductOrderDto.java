package com.huzheng.dto;

import com.huzheng.entity.Product;

import java.math.BigDecimal;

/**
 * @Author 胡正
 * @Date 2020/4/15 14:05
 * @Description 单个商品的信息
 */
public class OneProductOrderDto extends Product {
    private BigDecimal reduceAmount;
    private BigDecimal nowAmount;
    private String promotionName;

}
