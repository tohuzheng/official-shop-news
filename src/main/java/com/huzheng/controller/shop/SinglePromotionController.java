package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.SinglePromotion;
import com.huzheng.service.ISinglePromotionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * (SinglePromotion)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 17:44:38
 */
@RestController
@RequestMapping("singlePromotion")
public class SinglePromotionController {
    /**
     * 服务对象
     */
    @Autowired
    private ISinglePromotionService singlePromotionService;

    /**
     * @author zheng.hu
     * @date 2020/4/3 17:57
     * @description 分页查询
     * @param page
     * @param singlePromotion
     */
    @PostMapping("/querySinglePromotionPage")
    public Page<SinglePromotion> queryPageByEntity(Page page, SinglePromotion singlePromotion){
        return singlePromotionService.queryPromotionPage(page, singlePromotion);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/3 19:15
     * @description  修改单品促销信息
     * @param singlePromotion 实体类
     */
    @PostMapping("/updateSinglePromotionInfo")
    public void updateSinglePromotionInfo(SinglePromotion singlePromotion){
        if (singlePromotion.getId() == null) {
            throw new RuntimeException("id参数不可为空");
        }
        singlePromotionService._updateById(singlePromotion);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/3 19:51
     * @description 添加单品促销活动
     * @param singlePromotion
     */
    @PostMapping("/addSinglePromotion")
    public void addSinglePromotion(SinglePromotion singlePromotion){
        singlePromotion.setCreateDate(new Date());
        this.singlePromotionService._insert(singlePromotion);
    }

}