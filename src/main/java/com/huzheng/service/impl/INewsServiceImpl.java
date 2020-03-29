package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

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
}