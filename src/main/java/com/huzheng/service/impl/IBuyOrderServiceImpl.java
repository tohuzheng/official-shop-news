package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.enums.SinglePromotionType;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.dto.BuyOrderDto;
import com.huzheng.dto.QueryBuyOrderDto;
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
    private IProductService productService;

    @Autowired
    private ISinglePromotionService singlePromotionService;

    @Autowired
    private ICouponService couponService;

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
     * @description 结算生成订单
     * @param buycarIds
     */
    @Transactional
    @Override
    public void generateOrder(Integer[] buycarIds) {
        QueryWrapper<Buycar> queryCarParam = new QueryWrapper<>();
        queryCarParam.in("id", buycarIds);
        List<Buycar> buycars = buycarService._selectList(queryCarParam);
        // 订单明细
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Buycar buycar : buycars) {
            OrderDetail orderDetail = computerOneCar(buycar);
            orderDetails.add(orderDetail);
        }
        BigDecimal sumAmount = new BigDecimal("0");
        BigDecimal sumReduceAmount = new BigDecimal("0");
        for (OrderDetail item : orderDetails) {
            sumAmount = sumAmount.add(item.getSumAmount());
            sumReduceAmount = sumReduceAmount.add(item.getReduceAmount());
        }
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setOrderStatus(0);
        buyOrder.setBuyTime(new Date());
        buyOrder.setOrderCode("S"+System.currentTimeMillis());
        buyOrder.setPayStatus(0);
        buyOrder.setPaySumMoney(sumAmount);
        buyOrder.setReduceSumMoney(sumReduceAmount);
        buyOrder.setProductSumNumber(buycarIds.length);
        this._insert(buyOrder);
        int orderId = buyOrder.getId();
        for (OrderDetail item : orderDetails) {
            item.setOrderId(orderId);
            orderDetailService._insert(item);
        }
    }

    private OrderDetail computerOneCar(Buycar buycar){
        OrderDetail orderDetail = new OrderDetail();
        int num = buycar.getProductNumber();
        BigDecimal price = buycar.getPrice();
        BigDecimal discountAmount = buycar.getDiscountAmount();
        Integer singlePromotionId = buycar.getSinglePromotionId();
        BigDecimal sumMoney = price.multiply(new BigDecimal(num+""));
        BigDecimal reduceSumMoney = new BigDecimal("0");

        if (singlePromotionId != null) {
            // 促销活动
            SinglePromotion singlePromotion = singlePromotionService._selectById(singlePromotionId);
            if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_REDUCE.getValue())) {
                // 单品直降
                BigDecimal reduceMoney = singlePromotion.getReduceMoney();
                BigDecimal reduce = reduceMoney.multiply(new BigDecimal(num+""));
                reduceSumMoney=reduceSumMoney.add(reduce);
                sumMoney = sumMoney.subtract(reduce);
            }
            if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_GIVES.getValue())) {
                // 单品赠品
                orderDetail.setPresenterId(singlePromotion.getPresenterId());

            }
            if (singlePromotion.getPromotionType().equals(SinglePromotionType.N_M.getValue())) {
                // n元m件
                int number = singlePromotion.getProductNum();
                BigDecimal money = singlePromotion.getSumMoney();
                if (number <= buycar.getProductNumber()) {
                    int i = buycar.getProductNumber() / number;
                    BigDecimal pre = money.multiply(new BigDecimal(i));
                    int lastNum = buycar.getProductNumber() - i*number;
                    BigDecimal last = buycar.getPrice().multiply(new BigDecimal(lastNum+""));
                    reduceSumMoney = sumMoney.subtract(pre.add(last));
                    sumMoney = sumMoney.subtract(reduceSumMoney);
                }
            }
        }

        if (discountAmount != null) {
            // 打折活动
            BigDecimal tempSum = new BigDecimal(sumMoney.floatValue()+"");
            BigDecimal discountAfter = sumMoney.multiply(discountAmount.multiply(new BigDecimal("0.1")));
            reduceSumMoney.add(tempSum.subtract(discountAfter));
        }
        orderDetail.setReduceAmount(reduceSumMoney);
        orderDetail.setSumAmount(sumMoney);
        BeanUtil.copyProperties(buycar, orderDetail);
        orderDetail.setId(null);
        return orderDetail;
    }
}