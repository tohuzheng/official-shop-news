package com.huzheng.controller.shop;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSON;
import com.huzheng.entity.Customer;
import com.huzheng.entity.Product;
import com.huzheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/4/11 11:45
 * @Description 产品对比
 */
@RestController
@RequestMapping("/compare")
public class ProductCompareController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/addCompare")
    public String addCompare(Integer productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer = (Customer) userInfo;
        Product product = productService._selectById(productId);
        String productStr = JSON.toJSONString(product);
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(2);
        Map<String, String> map = jedis.hgetAll(customer.getUsername());
        if (map != null && map.size()<5) {
            jedis.hset(customer.getUsername(), productId+"", productStr);
            jedis.close();
            return "ok";
        }else {
            jedis.close();
            return "最多5个产品一起对比，请先删除一个再添加";
        }

    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 18:54
     * @description 根据用户名获取客户的对比数据
     * @param request
     */
    @GetMapping("/getCompare")
    public List<Product> getCompare(HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer userInfo = (Customer)session.getAttribute("userInfo");
        return productService.getProductCompareByCustomerId(userInfo.getUsername());
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 19:08
     * @description 删除一个比较数据
     * @param request
     * @param id
     */
    @PostMapping("/deleteOneCompare")
    public void deleteOneCompare(HttpServletRequest request,Integer id){
        HttpSession session = request.getSession();
        Customer userInfo = (Customer)session.getAttribute("userInfo");
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(2);
        jedis.hdel(userInfo.getUsername(), id+"");
        jedis.close();
    }
}
