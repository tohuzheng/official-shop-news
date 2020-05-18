package com.huzheng.controller.shop;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.ExcelSaveUtils;
import com.huzheng.dto.OnlyCodeVerifyDto;
import com.huzheng.entity.OnlyCode;
import com.huzheng.service.IOnlyCodeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * (OnlyCode)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-23 17:32:19
 */
@RestController
@RequestMapping("onlyCode")
public class OnlyCodeController {
    /**
     * 服务对象
     */
    @Autowired
    private IOnlyCodeService onlyCodeService;

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("deleteById")
    public Boolean deleteById(Integer id) {
        if (id == null) {
            throw new RuntimeException("ID为空，删除失败");
        }
        return this.onlyCodeService.deleteById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 17:44
     * @description 分页查询数据
     */
    @PostMapping("/queryOnlyCodePage")
    public Page<OnlyCode> queryOnlyCodePage(Page page, OnlyCode onlyCode){
        return onlyCodeService.queryOnlyCodePage(page, onlyCode);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 17:47
     * @description 添加防伪码
     */
    @PostMapping("/addOnlyCode")
    public void addOnlyCode(OnlyCode onlyCode) {
        if (StrUtil.isEmpty(onlyCode.getUuid())) {
            throw new RuntimeException("防伪码不可为空");
        }
        QueryWrapper<OnlyCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", onlyCode.getUuid());
        OnlyCode result = this.onlyCodeService._selectOne(queryWrapper);
        if (result == null) {
            this.onlyCodeService._insert(onlyCode);
        }else {
            throw new RuntimeException("该防伪码已存在，请确保防伪码唯一");
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/3/31 14:24
     * @description 防伪查询
     * @param uuid
     */
    @PostMapping("/queryOnlyCode")
    public OnlyCodeVerifyDto queryOnlyCode (String uuid) {
        OnlyCodeVerifyDto dto = this.onlyCodeService.queryOnlyCodeByUuid(uuid);
        return dto;
    }

    @PostMapping("/excelImport")
    public String excelImportCode(MultipartFile file) {
        String excelPath = null;
        try {
            excelPath = ExcelSaveUtils.saveExcel(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.onlyCodeService.excelAddCode(excelPath);
        return "ok";
    }

}