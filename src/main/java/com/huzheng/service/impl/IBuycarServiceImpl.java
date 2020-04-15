package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.commoms.enums.SinglePromotionType;
import com.huzheng.commoms.enums.UseTypeEnums;
import com.huzheng.dto.AddCarDto;
import com.huzheng.dto.ClearingBillDto;
import com.huzheng.dto.OrderProductComputerDto;
import com.huzheng.entity.*;
import com.huzheng.dao.IBuycarDao;
import com.huzheng.service.*;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 购物车(Buycar)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-11 11:05:25
 */
@Service("buycarService")
public class IBuycarServiceImpl extends IBaseServiceImpl<IBuycarDao, Buycar> implements IBuycarService {

    @Autowired
    private IBuycarDao buycarDao;

    @Autowired
    private IProductService productService;

    @Autowired
    private ISinglePromotionService singlePromotionService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ICouponGetListService couponGetListService;


    @Override
    public void addCar(AddCarDto addCarDto,Integer customerId) {
        QueryWrapper<Buycar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",addCarDto.getProductId());
        queryWrapper.eq("customer_id",customerId);
        Buycar buycar1 = this._selectOne(queryWrapper);
        if (buycar1 == null) {
            Product product = productService._selectById(addCarDto.getProductId());
            Buycar buycar = new Buycar();
            buycar.setPrice(product.getPrice());
            buycar.setProductName(product.getName());
            buycar.setProductImgurl(product.getImgUrl());
            buycar.setProductSize(product.getProductSize());
            buycar.setDiscountAmount(addCarDto.getDiscountAmount());
            buycar.setProductNumber(addCarDto.getProductCount());
            buycar.setSinglePromotionId(addCarDto.getPromotionId());
            buycar.setProductId(addCarDto.getProductId());
            buycar.setCustomerId(customerId);
            this._insert(buycar);
        }else {
            Integer num = buycar1.getProductNumber();
            buycar1.setProductNumber(num + addCarDto.getProductCount());
            this._updateById(buycar1);
        }
    }

    @Override
    public void deleteListByIds(List<Integer> ids) {
        for (Integer id : ids) {
            this._deleteById(id);
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 16:24
     * @description 生成订单
     * @param ids
     */
    @Override
    public ClearingBillDto generateClearingBill(Integer[] ids) {
        ClearingBillDto dto = new ClearingBillDto();
        QueryWrapper<Buycar> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Buycar> buycarList = this._selectList(queryWrapper);

        List<OrderProductComputerDto> computerDtoList = new ArrayList<>();
        Set<Integer> couponSet = new HashSet<>();
        for (Buycar buycar:buycarList) {
            OrderProductComputerDto computerDto = computerOneProduct(buycar);
            Coupon coupon = checkCustomerCoupon(buycar);
            if (coupon != null) {
                couponSet.add(coupon.getId());
            }
            if (computerDto != null) {
                computerDtoList.add(computerDto);
            }

        }
        dto.setOrderProductComputerDtos(computerDtoList);
        if (couponSet.size()>0) {
            QueryWrapper<Coupon> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.in("id", couponSet);
            dto.setUsableCoupon(couponService._selectList(queryWrapper1));
        }
        BigDecimal bigDecimal = finalMoney(dto);
        dto.setOrderSumMoney(bigDecimal);

        return dto;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 16:40
     * @description 计算每一条购物车记录的数据
     * @param buycar
     */
    private OrderProductComputerDto computerOneProduct (Buycar buycar) {
        OrderProductComputerDto dto = new OrderProductComputerDto();
        BigDecimal sumMoney = new BigDecimal("0");
        BigDecimal reduceMoney = new BigDecimal("0");
        if (buycar == null) {
            return null;
        }else {
            BigDecimal discountAmount = buycar.getDiscountAmount();
            if (discountAmount != null) {
                Integer productNumber = buycar.getProductNumber();
                BigDecimal price = buycar.getPrice();
                BigDecimal temp = price.multiply(new BigDecimal(productNumber + ""));
                sumMoney = temp.multiply(discountAmount.multiply(new BigDecimal("0.1")));
                reduceMoney = temp.subtract(sumMoney);
            }
            Integer singlePromotionId = buycar.getSinglePromotionId();
            if (singlePromotionId != null) {
                SinglePromotion singlePromotion = singlePromotionService._selectById(singlePromotionId);

                if (singlePromotion.getStartTime().getTime() < System.currentTimeMillis()
                    && singlePromotion.getOverTime().getTime() > System.currentTimeMillis()) {
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_REDUCE.getValue())) {
                        // 单品直降
                        BigDecimal tempReduceMoney = singlePromotion.getReduceMoney();
                        sumMoney = sumMoney.subtract(tempReduceMoney);
                        reduceMoney = reduceMoney.add(tempReduceMoney);
                    }
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_GIVES.getValue())) {
                        // 单品赠品
                        Product product = productService._selectById(singlePromotion.getPresenterId());
                        dto.setProduct(product);
                    }
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.N_M.getValue())) {
                        // 置0
                        sumMoney = new BigDecimal("0");
                        // n元m件
                        Integer num = singlePromotion.getProductNum();
                        BigDecimal money = singlePromotion.getSumMoney();

                        Integer productNumber = buycar.getProductNumber();
                        BigDecimal price = buycar.getPrice();
                        int n = productNumber/num;
                        int lastNum = productNumber-n*num;
                        BigDecimal tempM = money.multiply(new BigDecimal(productNumber+""));
                        BigDecimal lastMoney = price.multiply(new BigDecimal(lastNum+""));
                        sumMoney = tempM.add(lastMoney);
                        if (buycar.getDiscountAmount() != null) {
                            sumMoney = sumMoney.multiply(buycar.getDiscountAmount().multiply(new BigDecimal("0.1")));
                            Integer productNumber2 = buycar.getProductNumber();
                            BigDecimal price2 = buycar.getPrice();
                            BigDecimal temp = price.multiply(new BigDecimal(productNumber + ""));
                            reduceMoney = temp.subtract(sumMoney);
                        }
                    }
                }
            }

            if (discountAmount == null && singlePromotionId == null) {
                sumMoney = buycar.getPrice().multiply(new BigDecimal(buycar.getProductNumber()+""));
            }

            dto.setReduceMoney(reduceMoney);
            dto.setSumMoney(sumMoney);
            BeanUtil.copyProperties(buycar, dto);
            return dto;
        }
    }

    private Coupon checkCustomerCoupon(Buycar buycar) {
        List<CouponGetList> couponGetLists = couponGetListService.queryListByCustomerId(buycar.getCustomerId());
        // 查看是否有能使用的优惠券
        Product product = productService._selectById(buycar.getProductId());
        for (CouponGetList item : couponGetLists) {
            Coupon coupon = couponService.queryOneByIdAndProduct(item.getCouponId(), product);
            if (coupon != null) {
                return coupon;
            }
        }

        return null;
    }

    private BigDecimal finalMoney(ClearingBillDto dto) {
        List<OrderProductComputerDto> orderProductComputerDtos = dto.getOrderProductComputerDtos();
        List<Coupon> usableCoupon = dto.getUsableCoupon();
        BigDecimal finalMoney = new BigDecimal("0");

        for (OrderProductComputerDto opc: orderProductComputerDtos) {
            finalMoney = finalMoney.add(opc.getSumMoney());
        }

        if (CollUtil.isNotEmpty(usableCoupon)) {
            for (Coupon coupon:usableCoupon) {
                // 判断是否全场通用
                if (coupon.getUseType().equals(UseTypeEnums.ALL_PRODUCT.getValue())) {
                    if (coupon.getMinPoint().subtract(finalMoney).floatValue() <= 0) {
                        finalMoney = finalMoney.subtract(coupon.getAmount());
                        return finalMoney;
                    }
                }else if (coupon.getUseType().equals(UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue())) {
                    // 判断品类通用

                }

            }
        }

        return finalMoney;

    }
}