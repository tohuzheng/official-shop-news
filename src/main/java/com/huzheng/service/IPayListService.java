package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.PayList;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (PayList)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-07 18:31:48
 */
public interface IPayListService extends IBaseService<PayList> {

    Page<PayList> queryPage(Page page, PayList payList);
}