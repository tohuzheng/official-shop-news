package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.PayList;
import com.huzheng.service.IPayListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (PayList)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-07 18:31:48
 */
@RestController
@RequestMapping("payList")
public class PayListController {
    /**
     * 服务对象
     */
    @Autowired
    private IPayListService payListService;

    /**
     * @author zheng.hu
     * @date 2020/4/7 22:48
     * @description 分页查询
     * @param page
     * @param payList
     */
    @PostMapping("/queryPayListPage")
    Page<PayList> queryPayListPage(Page page, PayList payList) {
        return payListService.queryPage(page, payList);
    }


}