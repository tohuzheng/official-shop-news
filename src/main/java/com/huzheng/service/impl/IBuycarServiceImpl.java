package com.huzheng.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.commoms.enums.SinglePromotionType;
import com.huzheng.commoms.enums.UseTypeEnums;
import com.huzheng.dto.AddCarDto;
import com.huzheng.dto.ConfirmOrderDto;
import com.huzheng.dto.OrderProductComputerDto;
import com.huzheng.entity.*;
import com.huzheng.dao.IBuycarDao;
import com.huzheng.service.*;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    private ICouponProductCategoryRelationService categoryRelationService;

    @Autowired
    private ICouponProductRelationService couponProductRelationService;


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
            buycar.setProductClassName(product.getProductClassName());
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
     * @description 确认订单
     * @param ids
     */
    @Override
    public ConfirmOrderDto generateClearingBill(Integer[] ids,Integer customerId) {
        ConfirmOrderDto dto = new ConfirmOrderDto();
        QueryWrapper<Buycar> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Buycar> buycarList = this._selectList(queryWrapper);
        // 计算每件商品金额，优惠
        List<OrderProductComputerDto> computerDtoList = new ArrayList<>();
        for (Buycar buycar:buycarList) {
            OrderProductComputerDto computerDto = computerOneProduct(buycar);
            if (computerDto != null) {
                computerDtoList.add(computerDto);
            }
        }
        dto.setOrderProductComputerDtos(computerDtoList);

        // 计算整个订单的金额和优惠总金额
        BigDecimal bigDecimal = finalMoney(dto);
        dto.setOrderSumMoney(bigDecimal);

        // 找出能使用的优惠券
        Set<Coupon> coupons = checkCustomerCoupon(dto, customerId);
        if (coupons.size()>0) {
            dto.setUsableCoupon(coupons);
        }

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
            // 产品数量
            int num = buycar.getProductNumber();
            // 产品原单价
            BigDecimal price = buycar.getPrice();
            // 原本总价
            sumMoney = price.multiply(new BigDecimal(num+""));

            // 计算促销活动
            if (buycar.getSinglePromotionId() != null) {
                SinglePromotion singlePromotion = singlePromotionService._selectById(buycar.getSinglePromotionId());
                if (singlePromotion.getStartTime().getTime() < System.currentTimeMillis()
                        && singlePromotion.getOverTime().getTime() > System.currentTimeMillis()) {
                    // 促销活动没有过期
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_REDUCE.getValue())) {
                        // 单品直降
                        BigDecimal tempReduceMoney = singlePromotion.getReduceMoney();
                        sumMoney = sumMoney.subtract(tempReduceMoney);
                        reduceMoney = reduceMoney.add(tempReduceMoney);
                    }
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.SINGLE_GIVES.getValue())) {
                        // 单品赠品
                        Product product = productService._selectById(singlePromotion.getPresenterId());
                        dto.setPresenterId(product.getId());
                        dto.setPresenterName(product.getName());
                    }
                    if (singlePromotion.getPromotionType().equals(SinglePromotionType.N_M.getValue())) {
                        // n元m件
                        int number = singlePromotion.getProductNum();
                        BigDecimal money = singlePromotion.getSumMoney();
                        if (number <= buycar.getProductNumber()) {
                            int m = buycar.getProductNumber() / number;
                            BigDecimal pre = money.multiply(new BigDecimal(m+""));
                            int lastNum = buycar.getProductNumber() - m*number;
                            BigDecimal last = buycar.getPrice().multiply(new BigDecimal(lastNum+""));
                            BigDecimal tempSum = pre.add(last);
                            reduceMoney = sumMoney.subtract(tempSum);
                            sumMoney = tempSum;
                        }
                    }
                }
            }

            // 计算打折活动
            if (buycar.getDiscountAmount() != null) {
                BigDecimal discountAmount = buycar.getDiscountAmount();
                BigDecimal temp = sumMoney.multiply(discountAmount.multiply(new BigDecimal("0.1")));
                reduceMoney = reduceMoney.add(sumMoney.subtract(temp));
                sumMoney = temp;
            }

            dto.setReduceMoney(reduceMoney);
            dto.setSumMoney(sumMoney);
            dto.setBuycarId(buycar.getId());
            BeanUtil.copyProperties(buycar, dto);

            return dto;
        }
    }

    /**
     * @author zheng.hu
     * @date 2020/4/16 11:15
     * @description 找出该用户能用的优惠券
     * @param confirmOrderDto
     */
    private Set<Coupon> checkCustomerCoupon(ConfirmOrderDto confirmOrderDto, Integer customerId) {
        // 查出该用户所有获取的优惠券
        List<CouponGetList> couponGetLists = couponGetListService.queryListByCustomerId(customerId);
        Set<Coupon> coupons = new HashSet<>();
        for (CouponGetList itemCoupon : couponGetLists) {
            Coupon coupon = couponGetListService.queryOneCouponById(itemCoupon.getId());
            if (coupon.getUseType().equals(UseTypeEnums.ALL_PRODUCT.getValue())) {
                // 全场通用
                BigDecimal flag = confirmOrderDto.getOrderSumMoney().subtract(coupon.getMinPoint());
                if (flag.floatValue() >= 0) {
                    coupons.add(coupon);
                }
            }
            if (coupon.getUseType().equals(UseTypeEnums.RESTRICT_PRODUCT_CATEGORY.getValue())) {
                // 指定品类
                CouponProductCategoryRelation categoryRelation = categoryRelationService.queryOneByCouponId(coupon.getId());
                List<OrderProductComputerDto> dtoList = confirmOrderDto.getOrderProductComputerDtos();
                BigDecimal tempSum = new BigDecimal("0");
                for (OrderProductComputerDto dto:dtoList) {
                    if (categoryRelation.getProductClassName().equals(dto.getProductClassName())) {
                        tempSum = tempSum.add(dto.getSumMoney());
                    }
                }
                BigDecimal flag = tempSum.subtract(coupon.getMinPoint());
                if (flag.floatValue() >= 0) {
                    coupons.add(coupon);
                }
            }
            if (coupon.getUseType().equals(UseTypeEnums.RESTRICT_PRODUCT.getValue())) {
                // 指定商品
                CouponProductRelation productRelation = couponProductRelationService.queryOneByCouponId(coupon.getId());
                List<OrderProductComputerDto> dtoList = confirmOrderDto.getOrderProductComputerDtos();
                for (OrderProductComputerDto dto:dtoList) {
                    if (productRelation.getProductId().equals(dto.getProductId())) {
                        BigDecimal flag = dto.getSumMoney().subtract(coupon.getMinPoint());
                        if (flag.floatValue()>=0) {
                            coupons.add(coupon);
                        }
                    }
                }
            }
        }

        return coupons;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/16 11:18
     * @description 计算最终订单金额
     * @param dto
     */
    private BigDecimal finalMoney(ConfirmOrderDto dto) {
        List<OrderProductComputerDto> list = dto.getOrderProductComputerDtos();
        BigDecimal finalMoney = new BigDecimal("0");
        for (OrderProductComputerDto opc: list) {
            finalMoney = finalMoney.add(opc.getSumMoney());
        }

        return finalMoney;
    }
}