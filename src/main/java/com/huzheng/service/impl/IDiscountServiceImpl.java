package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.Discount;
import com.huzheng.dao.IDiscountDao;
import com.huzheng.service.IDiscountService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 促销活动-打折(Discount)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 21:17:04
 */
@Service("discountService")
public class IDiscountServiceImpl extends IBaseServiceImpl<IDiscountDao, Discount> implements IDiscountService {

    @Autowired
    private IDiscountDao discountDao;

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
     * @date 2020/4/3 21:25
     * @description 分页查询
     * @param page
     * @param discount
     */
    @Override
    public Page<Discount> queryPage(Page page, Discount discount) {

        QueryWrapper<Discount> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(discount);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);
        Page iPage = (Page) this._selectPage(page, queryWrapper);

        return iPage;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 15:26
     * @description 获取推送首页或者品类banner活动
     * @param
     */
    @Override
    public List<Discount> getDiscountListByFlag(String columnName) {
        QueryWrapper<Discount> queryWrapper = new QueryWrapper<>();
        if ("is_push_home_banner".equals(columnName)) {
            queryWrapper.eq("is_push_home_banner",1);
        }else {
            queryWrapper.eq("is_push_category_banner",1);
        }
        queryWrapper.eq("is_effective",1);
        queryWrapper.ge("over_time", new Date());
        List<Discount> list = this._selectList(queryWrapper);
        return list;
    }

}