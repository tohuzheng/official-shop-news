package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.ProductClass;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 产品类目(ProductClass)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-03-13 23:48:22
 */
public interface IProductClassDao extends BaseMapper<ProductClass> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductClass queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductClass> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param productClass 实例对象
     * @return 对象列表
     */
    List<ProductClass> queryAll(ProductClass productClass);

    /**
     * 新增数据
     *
     * @param productClass 实例对象
     * @return 影响行数
     */
    int insertProductClass(ProductClass productClass);

    /**
     * 修改数据
     *
     * @param productClass 实例对象
     * @return 影响行数
     */
    int updateProductClass(ProductClass productClass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIdProductClass(Integer id);

}