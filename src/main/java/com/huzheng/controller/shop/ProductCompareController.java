package com.huzheng.controller.shop;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSON;
import com.huzheng.entity.Customer;
import com.huzheng.entity.Product;
import com.huzheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void addCompare(Integer productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer = (Customer) userInfo;
        Product product = productService._selectById(productId);
        String productStr = JSON.toJSONString(product);
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(2);
        jedis.hset(customer.getUsername(), productId+"", productStr);
        jedis.close();
    }

    @GetMapping("/getCompare")
    public List<Product> getCompare(HttpServletRequest request){
        List<Product> products = new ArrayList<>();
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer = (Customer) userInfo;
        Jedis jedis = RedisDS.create().getJedis();
        jedis.select(2);
        Map<String, String> map = jedis.hgetAll(customer.getUsername());
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String val = map.get(key);
            Product p = (Product) JSON.parse(val);
            products.add(p);
        }

        return products;
    }
}
