package com.huzheng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Suggest;
import com.huzheng.service.base.IBaseService;

/**
 * (Suggest)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-29 17:06:38
 */
public interface ISuggestService extends IBaseService<Suggest> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/29 17:14
     * @description 分页查询
     */
    Page queryPage(Page page);
    
}