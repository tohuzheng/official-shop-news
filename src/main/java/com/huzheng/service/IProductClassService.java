package com.huzheng.service;

import com.huzheng.entity.ProductClass;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 产品类目(ProductClass)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-13 23:48:22
 */
public interface IProductClassService extends IBaseService<ProductClass> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductClass queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductClass> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param productClass 实例对象
     * @return 实例对象
     */
    ProductClass insert(ProductClass productClass);

    /**
     * 修改数据
     *
     * @param productClass 实例对象
     * @return 实例对象
     */
    ProductClass update(ProductClass productClass);

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
     * @param productClass 条件
     * @return 查询结果集合
     */
    List<ProductClass> queryAllByCondition(ProductClass productClass);

    /**
     * @author zheng.hu
     * @date 2020/3/13 23:51
     * @description 查询所有商品
     */
    List<String> queryAllProductClass();

}