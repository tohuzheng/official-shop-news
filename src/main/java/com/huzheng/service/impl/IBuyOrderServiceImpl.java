package com.huzheng.service.impl;

import com.huzheng.entity.BuyOrder;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.service.IBuyOrderService;
import com.huzheng.service.base.IBaseServiceImpl;
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
    
}