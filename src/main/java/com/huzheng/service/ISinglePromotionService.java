package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.SinglePromotion;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (SinglePromotion)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 17:44:38
 */
public interface ISinglePromotionService extends IBaseService<SinglePromotion> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/4/3 17:51
     * @description 分页查询数据
     * @param page
     * @param singlePromotion
     */
    Page<SinglePromotion> queryPromotionPage(Page page, SinglePromotion singlePromotion);
    
}