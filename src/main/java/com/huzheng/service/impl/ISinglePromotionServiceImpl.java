package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.SinglePromotion;
import com.huzheng.dao.ISinglePromotionDao;
import com.huzheng.service.ISinglePromotionService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SinglePromotion)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 17:44:38
 */
@Service("singlePromotionService")
public class ISinglePromotionServiceImpl extends IBaseServiceImpl<ISinglePromotionDao, SinglePromotion> implements ISinglePromotionService {

    @Autowired
    private ISinglePromotionDao singlePromotionDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {

        return this._deleteById(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/3 17:53
     * @description 分页查询
     * @param page
     * @param singlePromotion
     */
    @Override
    public Page<SinglePromotion> queryPromotionPage(Page page, SinglePromotion singlePromotion) {
        QueryWrapper<SinglePromotion> queryWrapper = new QueryWrapper<>();
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(singlePromotion);
        queryWrapper.allEq(CloumnNameUtils.toUnder(stringObjectMap), false);
        Page iPage = (Page) this._selectPage(page, queryWrapper);
        return iPage;
    }
}