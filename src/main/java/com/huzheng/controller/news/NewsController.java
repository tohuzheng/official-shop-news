package com.huzheng.controller.news;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        news.setReadNumber(0);
        newsService.addNews(news);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 23:43
     * @description 根据id查询某条新闻
     */
    @GetMapping("/selectNewsById")
    public News selectNewsById(Integer id) {
        return newsService._selectById(id);
    }


    /**
     * @author zheng.hu
     * @date 2020/3/26 23:27
     * @description 修改新闻
     */
    @PostMapping("/updateNewsById")
    public void updateNewsById(News news) {
        newsService._updateById(news);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 23:26
     * @description 分页查询
     */
    @PostMapping("/queryNewsPage")
    public Page<News> queryPage(Page page,News news){
       return newsService.queryPage(page,news);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 23:45
     * @description 删除新闻根据id
     */
    @PostMapping("/deleteNewsById")
    public void deleteNewsById(Integer id){
        if (id != null) {
            newsService._deleteById(id);
        }
    }

}