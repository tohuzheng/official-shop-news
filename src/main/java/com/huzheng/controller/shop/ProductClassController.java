package com.huzheng.controller.shop;

import com.huzheng.commoms.utils.ResultModel;
import com.huzheng.entity.ProductClass;
import com.huzheng.service.IProductClassService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * 产品类目(ProductClass)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-13 23:48:22
 */
@RestController
@RequestMapping("productClass")
public class ProductClassController {
    /**
     * 服务对象
     */
    @Autowired
    private IProductClassService productClassService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ProductClass selectOne(Integer id) {
        return this.productClassService.queryById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/13 23:59
     * @description 查询所有类目
     */
    @GetMapping("queryAllProductClass")
    public ResultModel queryAllClassName(){
        List<String> strings = productClassService.queryAllProductClass();
        ResultModel rm = new ResultModel(strings,"200");
        return rm;
    }

}