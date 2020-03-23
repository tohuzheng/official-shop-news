package com.huzheng.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.OnlyCode;
import com.huzheng.dao.IOnlyCodeDao;
import com.huzheng.service.IOnlyCodeService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (OnlyCode)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-23 17:32:19
 */
@Service("onlyCodeService")
public class IOnlyCodeServiceImpl extends IBaseServiceImpl<IOnlyCodeDao, OnlyCode> implements IOnlyCodeService {

    @Autowired
    private IOnlyCodeDao onlyCodeDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.onlyCodeDao.deleteByIdOnlyCode(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 17:35
     * @description 分页查询带条件
     */
    @Override
    public Page<OnlyCode> queryOnlyCodePage(Page<OnlyCode> page,OnlyCode onlyCode) {
        QueryWrapper<OnlyCode> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(onlyCode.getUuid())) {
            queryWrapper.eq("uuid",onlyCode.getUuid());
        }
        IPage<OnlyCode> onlyCodeIPage = this._selectPage(page, queryWrapper);
        return (Page<OnlyCode>) onlyCodeIPage;
    }
}