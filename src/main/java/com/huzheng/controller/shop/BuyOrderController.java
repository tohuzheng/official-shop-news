package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.entity.BuyOrder;
import com.huzheng.service.IBuyOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Predicate;

/**
 * 订单(BuyOrder)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-14 23:51:33
 */
@RestController
@RequestMapping("buyOrder")
public class BuyOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private IBuyOrderService buyOrderService;

    @Autowired
    private IBuyOrderDao buyOrderDao;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BuyOrder selectOne(Integer id) {
        return this.buyOrderService.queryById(id);
    }


}