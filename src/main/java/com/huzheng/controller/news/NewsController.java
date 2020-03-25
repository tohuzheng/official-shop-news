package com.huzheng.controller.news;

import cn.hutool.core.util.StrUtil;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.entity.News;
import com.huzheng.service.INewsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (News)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-24 18:14:31
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
     * 添加新闻
     *
     * @param news
     */
    @PostMapping("addNews")
    public void addNews(News news) {
        if (StrUtil.isEmpty(news.getTitle())) {
            throw new CorrectException("参数为空，添加失败");
        }
        newsService.addNews(news);
    }

}