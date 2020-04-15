package com.huzheng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.dto.CouponDto;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.Product;
import com.huzheng.service.base.IBaseService;


/**
 * (Coupon)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
public interface ICouponService extends IBaseService<Coupon> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/4/6 17:30
     * @description 添加优惠券
     * @param couponDto
     */
    void addCoupon(CouponDto couponDto);


    /**
     * @author zheng.hu
     * @date 2020/4/7 9:15
     * @description 分页查询优惠券
     * @param page
     * @param coupon
     */
    Page queryPage(Page page,Coupon coupon);

    /**
     * @author zheng.hu
     * @date 2020/4/7 9:59
     * @description 查看优惠券详细
     * @param
     */
    CouponDto queryDetail(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/4/11 10:05
     * @description 商详页根据产品id查询优惠券
     * @param
     */
    Coupon queryOneByProductId(Integer productId);

    /**
     * @author zheng.hu
     * @date 2020/4/11 18:27
     * @description 根据优惠券id和产品查询优惠券是否能使用
     * @param id
     */
    Coupon queryOneByIdAndProduct(Integer id, Product product);
}