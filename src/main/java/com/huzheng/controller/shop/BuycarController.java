package com.huzheng.controller.shop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.dto.AddCarDto;
import com.huzheng.dto.ConfirmOrderDto;
import com.huzheng.entity.Buycar;
import com.huzheng.entity.Customer;
import com.huzheng.service.IBuycarService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * 购物车(Buycar)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-11 11:05:25
 */
@RestController
@RequestMapping("buycar")
public class BuycarController {
    /**
     * 服务对象
     */
    @Autowired
    private IBuycarService buycarService;

    /**
     * 添加购物车
     */
    @PostMapping("addCar")
    public void addCar(AddCarDto addCarDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer=null;
        if (userInfo instanceof Customer) {
            customer=(Customer) userInfo;
        }

        buycarService.addCar(addCarDto, customer.getId());
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 11:29
     * @description 删除一条记录
     * @param id
     */
    @PostMapping("deleteCarOne")
    public void deleteCarOne(Integer id){
        buycarService._deleteById(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 14:44
     * @description 获取购物车所有记录根据用户
     * @param request
     */
    @RequestMapping("/getBuycarInfo")
    public List<Buycar> getBuycarInfo(HttpServletRequest request){
        Customer userInfo = (Customer) request.getSession().getAttribute("userInfo");
        QueryWrapper<Buycar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", userInfo.getId());
        List<Buycar> buycarList = buycarService._selectList(queryWrapper);
        return buycarList;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 15:56
     * @description 批量删除
     * @param ids
     */
    @PostMapping("/deleteListByIds")
    public void deleteListByIds(Integer[] ids) {
        buycarService.deleteListByIds(Arrays.asList(ids));
    }

    /**
     * @author zheng.hu
     * @date 2020/4/11 16:21
     * @description 生成结算单
     * @param ids
     */
    @PostMapping("/generateClearingBill")
    public ConfirmOrderDto generateClearingBill(Integer[] ids, HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer userInfo = (Customer) session.getAttribute("userInfo");
        ConfirmOrderDto dto = buycarService.generateClearingBill(ids, userInfo.getId());
        return dto;
    }

}