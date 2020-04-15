package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.VisitCount;

/**
 * 访客统计(VisitCount)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-07 15:23:42
 */
public interface IVisitCountDao extends BaseMapper<VisitCount> {

    Integer querySumView();

}