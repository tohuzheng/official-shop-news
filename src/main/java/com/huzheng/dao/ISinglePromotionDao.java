package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.SinglePromotion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SinglePromotion)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-03 17:44:38
 */
public interface ISinglePromotionDao extends BaseMapper<SinglePromotion> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param singlePromotion 实例对象
     * @return 对象列表
     */
    List<SinglePromotion> queryAll(SinglePromotion singlePromotion);


}