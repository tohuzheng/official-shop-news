package com.huzheng.service;

import com.huzheng.entity.News;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (News)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-24 18:14:31
 */
public interface INewsService extends IBaseService<News> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/25 17:12
     * @description 添加新闻资讯
     */
    void addNews(News news);
    
}