package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.DeliveryAddress;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 收货地址表(DeliveryAddress)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-15 22:07:36
 */
public interface IDeliveryAddressDao extends BaseMapper<DeliveryAddress> {



}