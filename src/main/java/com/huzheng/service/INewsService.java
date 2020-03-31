package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * @author zheng.hu
     * @date 2020/3/26 23:20
     * @description 分页查询
     */
    Page<News> queryPage(Page page,News news);

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:28
     * @description 查询最热
     * @param type
     */
    List<News> queryMaxHotThree(Integer type);

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:53
     * @description 通过标题模糊查询
     * @param page
     * @param title
     */
    Page<News> searchByTitleLike(Page page, String title);

    /**
     * @author zheng.hu
     * @date 2020/3/31 19:56
     * @description 同步更新阅读数
     * @param
     */
    void syncNewsReadNumber();
    
}