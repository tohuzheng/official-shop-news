package com.huzheng.controller.shop;

import com.huzheng.entity.Customer;
import com.huzheng.entity.DeliveryAddress;
import com.huzheng.service.IDeliveryAddressService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 收货地址表(DeliveryAddress)表控制层
 *
 * @author zheng.hu
 * @since 2020-04-15 22:07:36
 */
@RestController
@RequestMapping("deliveryAddress")
public class DeliveryAddressController {
    /**
     * 服务对象
     */
    @Autowired
    private IDeliveryAddressService deliveryAddressService;

    @GetMapping("/queryAllAddress")
    public List<DeliveryAddress> queryAllAddress(HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer userInfo = (Customer) session.getAttribute("userInfo");
        return deliveryAddressService.queryAllAddress(userInfo.getId());
    }

    @PostMapping("/updateDelivery")
    public void updateDelivery(DeliveryAddress deliveryAddress){
        deliveryAddressService._updateById(deliveryAddress);
    }

    @PostMapping("/deleteDelivery")
    public void deleteDelivery(Integer id){
        deliveryAddressService._deleteById(id);
    }

    @PostMapping("/addDelivery")
    public void addDelivery(DeliveryAddress deliveryAddress,HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer userInfo = (Customer) session.getAttribute("userInfo");
        deliveryAddress.setCustomerId(userInfo.getId());
        deliveryAddressService._insert(deliveryAddress);
    }
}