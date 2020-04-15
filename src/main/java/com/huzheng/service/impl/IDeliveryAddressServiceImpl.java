package com.huzheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.commoms.exceptions.CorrectException;
import com.huzheng.dao.IDeliveryAddressDao;
import com.huzheng.entity.DeliveryAddress;
import com.huzheng.service.IDeliveryAddressService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址表(DeliveryAddress)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-15 22:07:36
 */
@Service("deliveryAddressService")
public class IDeliveryAddressServiceImpl extends IBaseServiceImpl<IDeliveryAddressDao,DeliveryAddress> implements IDeliveryAddressService {

    @Override
    public List<DeliveryAddress> queryAllAddress(Integer customerId) {
        if (customerId == null) {
            throw new CorrectException("参数错误，顾客id不可为空");
        }
        QueryWrapper<DeliveryAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", customerId);
        List<DeliveryAddress> deliveryAddresses = this._selectList(queryWrapper);
        return deliveryAddresses;
    }

}