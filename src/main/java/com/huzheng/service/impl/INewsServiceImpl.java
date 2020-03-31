package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.News;
import com.huzheng.dao.INewsDao;
import com.huzheng.service.INewsService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * (News)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-24 18:14:31
 */
@Service("newsService")
public class INewsServiceImpl extends IBaseServiceImpl<INewsDao, News> implements INewsService {

    @Autowired
    private INewsDao newsDao;

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
     * @date 2020/3/25 17:14
     * @description 添加新闻资讯
     */
    @Override
    public void addNews(News news) {
        news.setCreateDate(new Date());
        this._insert(news);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/26 23:20
     * @description 分页查询
     */
    @Override
    public Page<News> queryPage(Page page, News news) {
        QueryWrapper<News> queryWrapper =new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(news);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map),false);
        Page<News> iPage = (Page<News>) this._selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:29
     * @description 查询最热
     * @param type
     */
    @Override
    public List<News> queryMaxHotThree(Integer type) {
        return newsDao.queryMaxHotThree(type);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 16:54
     * @description 通过标题模糊查询
     * @param page
     * @param title
     */
    @Override
    public Page<News> searchByTitleLike(Page page, String title) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title);
        Page iPage = (Page) this._selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 19:57
     * @description 同步更新阅读数
     * @param
     */
    @Transactional
    @Override
    public void syncNewsReadNumber() {
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(1);
        Set<String> keys = jedis.keys("*");
        for (String key:keys) {
            String val = jedis.get(key);
            newsDao.updateReadNumber(Integer.parseInt(key), Integer.parseInt(val));
        }
        // 同步完毕，清空数据库
        jedis.flushDB();
        jedis.close();
    }
}