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
 * @since 2020-03-14 23:51:33
 */
@Service("buyOrderService")
public class IBuyOrderServiceImpl extends IBaseServiceImpl<IBuyOrderDao, BuyOrder> implements IBuyOrderService {

    @Autowired
    private IBuyOrderDao buyOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BuyOrder queryById(Integer id) {
        return this.buyOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BuyOrder> queryAllByLimit(int offset, int limit) {
        return this.buyOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param buyOrder 实例对象
     * @return 实例对象
     */
    @Override
    public BuyOrder insert(BuyOrder buyOrder) {
        this.buyOrderDao.insertBuyOrder(buyOrder);
        return buyOrder;
    }

    /**
     * 修改数据
     *
     * @param buyOrder 实例对象
     * @return 实例对象
     */
    @Override
    public BuyOrder update(BuyOrder buyOrder) {
        this.buyOrderDao.updateBuyOrder(buyOrder);
        return this.queryById(buyOrder.getId());
    }

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
     * 通过实体作为where条件查询
     *
     * @param buyOrder 条件
     * @return 查询结果集合
     */
    @Override
    public List<BuyOrder> queryAllByCondition(BuyOrder buyOrder) {
        return this.buyOrderDao.queryAll(buyOrder);
    }
}