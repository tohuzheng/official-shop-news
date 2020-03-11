package com.huzheng.service.impl;

import com.huzheng.entity.News;
import com.huzheng.dao.INewsDao;
import com.huzheng.service.INewsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (News)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-11 20:59:47
 */
@Service("newsService")
public class INewsServiceImpl implements INewsService {
    @Autowired
    private INewsDao newsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public News queryById(Integer id) {
        return this.newsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<News> queryAllByLimit(int offset, int limit) {
        return this.newsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param news 实例对象
     * @return 实例对象
     */
    @Override
    public News insert(News news) {
        this.newsDao.insertNews(news);
        return news;
    }

    /**
     * 修改数据
     *
     * @param news 实例对象
     * @return 实例对象
     */
    @Override
    public News update(News news) {
        this.newsDao.updateNews(news);
        return this.queryById(news.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.newsDao.deleteByIdNews(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 21:21
     * @description 通过实体类条件查询数，where条件属性不为空的
     */
    @Override
    public List<News> queryAllByCondition(News news) {
        return this.newsDao.queryAll(news);
    }
}