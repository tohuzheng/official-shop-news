package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.OrderStatus;
import com.huzheng.commoms.enums.PayStatus;
import com.huzheng.commoms.enums.SinglePromotionType;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.OrderProductComputerDto;
import com.huzheng.dto.QueryBuyOrderDto;
import com.huzheng.dto.SubmitOrderDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.entity.Buycar;
import com.huzheng.entity.OrderDetail;
import com.huzheng.entity.SinglePromotion;
import com.huzheng.service.*;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private IBuycarService buycarService;

    @Autowired
    private ICouponGetListService couponGetListService;

    @Autowired
    private IOrderDetailService orderDetailService;

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

    /**
     * @author zheng.hu
     * @date 2020/4/7 16:43
     * @description 查询某天的订单量
     * @param date
     */
    @Override
    public Integer queryOneDayOrderCount(Date date) {

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);

        QueryWrapper<BuyOrder> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.between("buy_time", beginOfDay, endOfDay);
        Integer count = this._selectCount(queryWrapper1);
        return count;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/15 14:38
     * @description 提交生成订单
     * @param submitOrderDto
     * @return 返回需要支付的金额
     */
    @Transactional
    @Override
    public void submitOrder(SubmitOrderDto submitOrderDto, String orderCode, String payAmount, Integer customerId) {
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setProductSumNumber(submitOrderDto.getDetailedList().size());
        buyOrder.setReduceSumMoney(reduceSumMoney(submitOrderDto.getDetailedList()));
        buyOrder.setPayStatus(PayStatus.PAYING.getValue());
        buyOrder.setOrderCode(orderCode);
        buyOrder.setBuyTime(new Date());
        buyOrder.setOrderStatus(OrderStatus.UN_DELIVER.getValue());
        buyOrder.setAddressId(submitOrderDto.getAddressId());
        buyOrder.setCustomerId(customerId);
        buyOrder.setPaySumMoney(new BigDecimal(payAmount));
        if (submitOrderDto.getCouponId() != null) {
            buyOrder.setCouponId(submitOrderDto.getCouponId());
            // 修改优惠券状态
            couponGetListService.changeUseStatusByCustomerIdAndCouponId(customerId, submitOrderDto.getCouponId());
        }
        this._insert(buyOrder);
        orderDetailService.batchAddOrderDetail(submitOrderDto.getDetailedList(), buyOrder.getId());
        buycarService.deleteListByIds(getBuycarIds(submitOrderDto.getDetailedList()));
    }

    @Override
    public void changeOrderStatusByCode(String orderCode) {
        QueryWrapper<BuyOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_code",orderCode);
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setPayStatus(1);
        this._update(buyOrder, queryWrapper);
    }

    private BigDecimal reduceSumMoney(List<OrderProductComputerDto> detailedList){
        BigDecimal sum = new BigDecimal("0");
        for (OrderProductComputerDto dto : detailedList) {
            sum = sum.add(dto.getReduceMoney());
        }
        return sum;
    }

    private List<Integer> getBuycarIds(List<OrderProductComputerDto> detailedList){
        List<Integer> list = new ArrayList<>();
        for (OrderProductComputerDto dto : detailedList) {
            list.add(dto.getBuycarId());
        }
        return list;
    }

}