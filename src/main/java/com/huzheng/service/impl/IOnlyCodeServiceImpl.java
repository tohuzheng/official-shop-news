package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dao.IProductDao;
import com.huzheng.dto.OnlyCodeVerifyDto;
import com.huzheng.entity.OnlyCode;
import com.huzheng.dao.IOnlyCodeDao;
import com.huzheng.entity.Product;
import com.huzheng.service.IOnlyCodeService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    @Autowired
    private IProductDao productDao;

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

    @Override
    public OnlyCodeVerifyDto queryOnlyCodeByUuid(String uuid) {
        OnlyCodeVerifyDto dto = new OnlyCodeVerifyDto();
        if (StrUtil.isEmpty(uuid)) {
            return null;
        }else {
            QueryWrapper<OnlyCode> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uuid", uuid);
            OnlyCode onlyCode = this.onlyCodeDao.selectOne(queryWrapper);
            if (onlyCode == null) {
                return null;
            }
            Product product = productDao.selectById(onlyCode.getProductId());
            BeanUtil.copyProperties(onlyCode, dto);
            BeanUtil.copyProperties(product, dto);
            return dto;
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 21:30
     * @description 使用excel批量导入
     * @param excelPath
     */
    @Override
    public void excelAddCode(String excelPath) {

        if (StrUtil.isNotEmpty(excelPath)) {
            ExcelReader reader = ExcelUtil.getReader(excelPath);
            List<OnlyCode> list = reader.readAll(OnlyCode.class);
            for (OnlyCode onlyCode : list) {
                this._insert(onlyCode);
            }
        }

    }
}