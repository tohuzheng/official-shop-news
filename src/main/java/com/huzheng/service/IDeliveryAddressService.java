package com.huzheng.service;

import com.huzheng.entity.DeliveryAddress;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 收货地址表(DeliveryAddress)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-15 22:07:36
 */
public interface IDeliveryAddressService extends IBaseService<DeliveryAddress> {

   List<DeliveryAddress> queryAllAddress(Integer customerId);
    
}