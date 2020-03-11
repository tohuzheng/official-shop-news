package com.huzheng.controller.news;

import com.huzheng.entity.News;
import com.huzheng.service.INewsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (News)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-11 20:59:47
 */
@RestController
@RequestMapping("news")
public class NewsController {
    /**
     * 服务对象
     */
    @Autowired
    private INewsService newsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public News selectOne(Integer id) {
        return this.newsService.queryById(id);
    }


}