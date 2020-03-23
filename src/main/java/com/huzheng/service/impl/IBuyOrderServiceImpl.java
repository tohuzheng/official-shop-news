package com.huzheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.entity.DeliveryAddress;
import com.huzheng.entity.OrderDetail;
import com.huzheng.service.IBuyOrderService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 订单(BuyOrder)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-23 10:40:31
 */
@Service("buyOrderService")
public class IBuyOrderServiceImpl extends IBaseServiceImpl<IBuyOrderDao,BuyOrder> implements IBuyOrderService {

    @Autowired
    private IBuyOrderDao buyOrderDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.buyOrderDao.deleteByIdBuyOrder(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/23 11:23
     * @description 分页查询
     */
    @Override
    public Page<BuyOrderDto> queryPage(QueryBuyOrderDto queryDto) {
        Page<BuyOrderDto> page =new Page();
        Integer offset = (queryDto.getCurrent()-1)*queryDto.getSize();
        Integer limit = offset+queryDto.getSize();
        List<BuyOrderDto> buyOrderDtos = buyOrderDao.queryAllPage(queryDto, offset, limit);
        List<BuyOrderDto> total = buyOrderDao.queryAllPage(queryDto, null, null);
        page.setTotal(total.size());
        page.setRecords(buyOrderDtos);
        return page;
    }
}