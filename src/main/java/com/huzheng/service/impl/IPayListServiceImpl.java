package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.CloumnNameUtils;
import com.huzheng.entity.PayList;
import com.huzheng.dao.IPayListDao;
import com.huzheng.service.IPayListService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * (PayList)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-07 18:31:48
 */
@Service("payListService")
public class IPayListServiceImpl extends IBaseServiceImpl<IPayListDao, PayList> implements IPayListService {

    @Autowired
    private IPayListDao payListDao;

    @Override
    public Page<PayList> queryPage(Page page, PayList payList) {

        QueryWrapper<PayList> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = BeanUtil.beanToMap(payList);
        queryWrapper.allEq(CloumnNameUtils.toUnder(map), false);

        return (Page<PayList>) this._selectPage(page, queryWrapper);
    }
}