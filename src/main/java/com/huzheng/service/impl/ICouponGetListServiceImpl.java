package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.CouponGetList;
import com.huzheng.dao.ICouponGetListDao;
import com.huzheng.service.ICouponGetListService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (CouponGetList)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-07 11:46:23
 */
@Service("couponGetListService")
public class ICouponGetListServiceImpl extends IBaseServiceImpl<ICouponGetListDao, CouponGetList> implements ICouponGetListService {

    /**
     * @author zheng.hu
     * @date 2020/4/7 11:54
     * @description 分页查询
     * @param page
     * @param couponGetList
     */
    @Override
    public Page<CouponGetList> queryPage(Page page, CouponGetList couponGetList) {
        QueryWrapper<CouponGetList> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(couponGetList);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);
        Page iPage = (Page) this._selectPage(page, queryWrapper);

        return iPage;
    }

    @Override
    public List<CouponGetList> queryListByCustomerId(Integer customerId) {
        QueryWrapper<CouponGetList> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("effective_time_start", new Date());
        queryWrapper.le("effective_time_over", new Date());
        queryWrapper.eq("customer_id", customerId);
        List<CouponGetList> couponGetLists = this._selectList(queryWrapper);
        return couponGetLists;
    }
}