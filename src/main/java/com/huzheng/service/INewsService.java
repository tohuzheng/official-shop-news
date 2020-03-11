package com.huzheng.service;

import com.huzheng.entity.News;
import java.util.List;

/**
 * (News)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-11 20:59:47
 */
public interface INewsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    News queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<News> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param news 实例对象
     * @return 实例对象
     */
    News insert(News news);

    /**
     * 修改数据
     *
     * @param news 实例对象
     * @return 实例对象
     */
    News update(News news);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/11 21:21
     * @description 通过实体类条件查询数，where条件属性不为空的
     */
    List<News> queryAllByCondition(News news);

}