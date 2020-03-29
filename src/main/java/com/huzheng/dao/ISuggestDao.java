package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Suggest;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Suggest)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-03-29 17:06:38
 */
public interface ISuggestDao extends BaseMapper<Suggest> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Suggest queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Suggest> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param suggest 实例对象
     * @return 对象列表
     */
    List<Suggest> queryAll(Suggest suggest);

    /**
     * 新增数据
     *
     * @param suggest 实例对象
     * @return 影响行数
     */
    int insertSuggest(Suggest suggest);

    /**
     * 修改数据
     *
     * @param suggest 实例对象
     * @return 影响行数
     */
    int updateSuggest(Suggest suggest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIdSuggest(Integer id);

}