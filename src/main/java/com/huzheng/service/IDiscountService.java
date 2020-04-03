package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Discount;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 促销活动-打折(Discount)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 21:17:04
 */
public interface IDiscountService extends IBaseService<Discount> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/4/3 21:24
     * @description 分页查询
     * @param page
     * @param discount
     */
    Page<Discount> queryPage(Page page,Discount discount);
    
}