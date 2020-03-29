package com.huzheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Suggest;
import com.huzheng.dao.ISuggestDao;
import com.huzheng.service.ISuggestService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Suggest)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-29 17:06:38
 */
@Service("suggestService")
public class ISuggestServiceImpl extends IBaseServiceImpl<ISuggestDao, Suggest> implements ISuggestService {

    @Autowired
    private ISuggestDao suggestDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.suggestDao.deleteByIdSuggest(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/29 17:15
     * @description 分页查询建议
     */
    @Override
    public Page<Suggest> queryPage(Page page) {
        QueryWrapper<Suggest> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page resultPage = (Page)this._selectPage(page, queryWrapper);
        return resultPage;
    }
}