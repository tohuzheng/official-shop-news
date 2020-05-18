package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huzheng.commoms.utils.ExcelSaveUtils;
import com.huzheng.dto.CouponDto;
import com.huzheng.entity.Coupon;
import com.huzheng.entity.Product;
import com.huzheng.service.ICouponService;
import com.huzheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * (Coupon)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@RestController
@RequestMapping("coupon")
public class CouponController {
    /**
     * 服务对象
     */
    @Autowired
    private ICouponService couponService;

    @Autowired
    private IProductService productService;

    /**
     * @author zheng.hu
     * @date 2020/4/6 17:02
     * @description 导入商品excel
     * @param file
     * @return excel中的商品id返回商品数据
     */
    @PostMapping("/excelImport")
    @ResponseBody
    public List<Product> importProductByExcel(MultipartFile file){
        String excelPath = null;
        try {
            excelPath = ExcelSaveUtils.saveExcel(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Product> products = this.productService.queryProductByExcel(excelPath);
        return products;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/6 17:11
     * @description 添加优惠券
     * @param
     */
    @PostMapping("/addCoupon")
    @ResponseBody
    public void addCoupon (@RequestBody CouponDto couponDto) {
        couponService.addCoupon(couponDto);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 9:14
     * @description 分页查询优惠券
     * @param
     */
    @PostMapping("/queryPage")
    @ResponseBody
    public Page<Coupon> queryPage(Page page, Coupon coupon) {
        return couponService.queryPage(page, coupon);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 9:51
     * @description 优惠券表更新
     * @param
     */
    @PostMapping("/updateCoupon")
    @ResponseBody
    public void updateCoupon(Coupon coupon) {
        couponService._updateById(coupon);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 9:51
     * @description 查看优惠券详细
     * @param
     */
    @PostMapping("/queryDetail")
    @ResponseBody
    public CouponDto queryDetail (Integer id) {

        return couponService.queryDetail(id);
    }

    @RequestMapping("/deleteCoupon")
    @ResponseBody
    public void deleteCoupon(Integer id) {
        couponService.deleteById(id);
    }


}