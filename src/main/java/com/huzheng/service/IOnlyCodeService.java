package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.OnlyCodeVerifyDto;
import com.huzheng.entity.OnlyCode;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * (OnlyCode)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-23 17:32:19
 */
public interface IOnlyCodeService extends IBaseService<OnlyCode> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/23 17:35
     * @description
     */
    Page<OnlyCode> queryOnlyCodePage(Page<OnlyCode> page, OnlyCode onlyCode);

    /**
     * @author zheng.hu
     * @date 2020/3/31 14:06
     * @description 根据uuid查询产品信息
     * @param uuid
     */
    OnlyCodeVerifyDto queryOnlyCodeByUuid(String uuid);

    /**
     * @author zheng.hu
     * @date 2020/3/31 21:24
     * @description 批量添加唯一防伪码
     * @param
     */
    void excelAddCode(String excelPath);
    
}