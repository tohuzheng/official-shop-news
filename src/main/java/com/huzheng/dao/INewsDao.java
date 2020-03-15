package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.News;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (News)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-03-11 20:59:47
 */
public interface INewsDao extends BaseMapper<News> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    News queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<News> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param news 实例对象
     * @return 对象列表
     */
    List<News> queryAll(News news);

    /**
     * 新增数据
     *
     * @param news 实例对象
     * @return 影响行数
     */
    int insertNews(News news);

    /**
     * 修改数据
     *
     * @param news 实例对象
     * @return 影响行数
     */
    int updateNews(News news);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIdNews(Integer id);

}