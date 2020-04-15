package com.huzheng.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.dao.IVisitCountDao;
import com.huzheng.entity.VisitCount;
import com.huzheng.service.IVisitCountService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 访客统计(VisitCount)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-07 15:23:42
 */
@Service("visitCountService")
public class IVisitCountServiceImpl extends IBaseServiceImpl<IVisitCountDao, VisitCount> implements IVisitCountService {

    @Autowired
    IVisitCountDao visitCountDao;

    @Override
    public Integer queryAllView() {
        return visitCountDao.querySumView();
    }

    @Override
    public Integer queryOneDayView(Date date) {
        Date beginOfDay = DateUtil.beginOfDay(date);
        QueryWrapper<VisitCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visit_date", beginOfDay);
        VisitCount visitCount = this._selectOne(queryWrapper);
        if (visitCount == null) {
            return 0;
        }
        return visitCount.getViewCount();
    }
}