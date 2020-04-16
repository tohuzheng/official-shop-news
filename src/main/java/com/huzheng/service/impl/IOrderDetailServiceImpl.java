package com.huzheng.service.impl;

import com.huzheng.dto.OrderProductComputerDto;
import com.huzheng.entity.OrderDetail;
import com.huzheng.dao.IOrderDetailDao;
import com.huzheng.service.IOrderDetailService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (OrderDetail)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-15 16:09:13
 */
@Service("orderDetailService")
public class IOrderDetailServiceImpl extends IBaseServiceImpl<IOrderDetailDao,OrderDetail> implements IOrderDetailService {

    /**
     * @author zheng.hu
     * @date 2020/4/16 18:41
     * @description 批量添加数据
     * @param detailedList
     */
    @Override
    public void batchAddOrderDetail(List<OrderProductComputerDto> detailedList,Integer orderId) {
        for (OrderProductComputerDto dto : detailedList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setSumAmount(dto.getSumMoney());
            orderDetail.setReduceAmount(dto.getReduceMoney());
            orderDetail.setBuyNumber(dto.getProductNumber());
            orderDetail.setPrice(dto.getPrice());
            orderDetail.setProductName(dto.getProductName());
            orderDetail.setProductSize(dto.getProductSize());
            orderDetail.setProductId(dto.getProductId());
            orderDetail.setProductImgUrl(dto.getImgurl());
            if (dto.getPresenterId() != null) {
                orderDetail.setPresenterId(dto.getPresenterId());
            }
            this._insert(orderDetail);
        }

    }
}