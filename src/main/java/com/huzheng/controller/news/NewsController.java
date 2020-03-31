package com.huzheng.controller.news;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.entity.News;
import com.huzheng.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import java.util.List;

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

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:29
     * @description 查询最热三条数据
     * @param newsType
     */
    @PostMapping("/queryMaxHotThree")
    public List<News> queryMaxHotThree(Integer newsType) {
        return newsService.queryMaxHotThree(newsType);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:52
     * @description 通过标题模糊分页查询
     * @param page
     * @param title
     */
    @PostMapping("/searchByTitleLike")
    public Page<News> searchByTitleLike(Page page, String title) {
        return newsService.searchByTitleLike(page, title);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 17:55
     * @description 统计阅读量
     * @param id 新闻的id
     */
    @PostMapping("/readNumberAdd")
    public void readNumberAdd(Integer id){
        if (id != null) {
            Jedis jedis = RedisDS.create().getJedis();
            jedis.select(1);
            jedis.incr(id+"");
            jedis.close();
        }
    }
}