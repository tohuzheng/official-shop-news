package com.huzheng.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.commoms.utils.ResultModel;
import com.huzheng.entity.ProductClass;
import com.huzheng.service.IProductClassService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
     * @author zheng.hu
     * @date 2020/3/13 23:59
     * @description 查询所有类目
     */
    @GetMapping("queryAllProductClass")
    public ResultModel queryAllClassName(){
        List<ProductClass> list = productClassService.queryAllProductClass();
        ResultModel rm = new ResultModel(list,"200");
        return rm;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/18 11:11
     * @description 添加数据
     */
    @PostMapping("/addProductClass")
    public void addProductClass(ProductClass productClass){
        productClassService._insert(productClass);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/18 11:11
     * @description 修改数据
     */
    @PostMapping("/updateProductClass")
    public void updateProductClass(ProductClass productClass){
        productClassService._updateById(productClass);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/18 11:16
     * @description 删除产品类目
     */
    @PostMapping("/deleteProductClass")
    public void deleteProductClass(ProductClass productClass){
        productClassService.deleteById(productClass.getId());
    }

    /**
     * @author zheng.hu
     * @date 2020/3/18 11:24
     * @description 条件分页查询page
     */
    @PostMapping("/queryPage")
    public IPage<ProductClass> queryPage(Page page,ProductClass productClass){
        Page<ProductClass> queryPage = new Page<>();
        QueryWrapper<ProductClass> queryWrapper = new QueryWrapper<>();
        queryPage.setCurrent(page.getCurrent());
        queryPage.setSize(page.getSize());
        Map<String, Object> map = BeanUtil.beanToMap(productClass);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map),false);
        IPage<ProductClass> resultPage = productClassService._selectPage(queryPage, queryWrapper);
        return resultPage;
    }
}