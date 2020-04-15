package com.huzheng.service;

import com.huzheng.entity.VisitCount;
import com.huzheng.service.base.IBaseService;

import java.util.Date;

/**
 * 访客统计(VisitCount)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-07 15:23:42
 */
public interface IVisitCountService extends IBaseService<VisitCount> {

    Integer queryAllView();

    Integer queryOneDayView(Date date);
    
}