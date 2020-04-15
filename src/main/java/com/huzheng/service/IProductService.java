package com.huzheng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huzheng.dto.ProductDetailDto;
import com.huzheng.entity.Product;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-11 21:24:11
 */
public interface IProductService extends IBaseService<Product> {



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    
     /**
     * 通过实体作为where条件查询
     *
     * @param product 条件
     * @return 查询结果集合
     */
    List<Product> queryAllByCondition(Product product);

    /**
     * @author zheng.hu
     * @date 2020/3/18 13:25
     * @description 通过实体类分页查询
     */
    IPage<Product> queryPageByEntity(Page page, Product product);

    /**
     * @author zheng.hu
     * @date 2020/4/1 0:01
     * @description 查询最新产品6个
     * @param
     */
    List<Product> queryNewProduct();

    /**
     * @author zheng.hu
     * @date 2020/4/1 0:01
     * @description 查询推荐产品6个，销量最高的
     * @param
     */
    List<Product> queryRecommendProduct();

    /**
     * @author zheng.hu
     * @date 2020/4/6 16:49
     * @description 通过excel中的商品id查询商品数据
     * @param
     */
    List<Product> queryProductByExcel(String excelPath);

    /**
     * @author zheng.hu
     * @date 2020/4/7 23:39
     * @description 查询产品详细
     * @param id
     */
    ProductDetailDto queryProductDetail(Integer id);

}