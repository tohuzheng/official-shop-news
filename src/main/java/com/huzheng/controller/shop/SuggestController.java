package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.entity.Suggest;
import com.huzheng.service.ISuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * (Suggest)表控制层
 *
 * @author zheng.hu
 * @since 2020-03-29 17:06:38
 */
@RestController
@RequestMapping("suggest")
public class SuggestController {
    /**
     * 服务对象
     */
    @Autowired
    private ISuggestService suggestService;

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("selectOne")
    public Boolean deleteById(Integer id) {

        return this.suggestService.deleteById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/29 17:13
     * @description 添加建议
     */
    @PostMapping("/addSuggest")
    public void addSuggest(Suggest suggest) {
        suggest.setCreateDate(new Date());
        this.suggestService._insert(suggest);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/29 17:22
     * @description 分页查询数据
     */
    @PostMapping("/queryPageSuggest")
    public Page<Suggest> queryPage(Page page) {
        return this.suggestService.queryPage(page);
    }

}