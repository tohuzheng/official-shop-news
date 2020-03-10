package com.huzheng.controller.shop;

import cn.hutool.core.util.StrUtil;
import com.huzheng.commoms.redis.RedisString;
import com.huzheng.commoms.utils.EmailUtils;
import com.huzheng.commoms.utils.ResultModel;
import com.huzheng.commoms.utils.RsaUtils;
import com.huzheng.entity.Customer;
import com.huzheng.entity.Login;
import com.huzheng.service.ICustomerService;
import com.huzheng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

/**
 * @Author 胡正
 * @Date 2020/1/20 10:35
 * @Description 客户登录控制器
 */
@RequestMapping("/consumer")
@Controller
public class CustomerLoginController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/checkLogin")
    @ResponseBody
    public Customer checkLogin(Customer customer, String token){
        String ciphertext = customer.getPassword();
        RedisString redisString = RedisString.getInstans();
        String privateKey = redisString.getValueByKey(token);
        try {
            String password = RsaUtils.decodeRsa(ciphertext, privateKey);
            customer.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer checkResult=customerService.chceckLogin(customer);
        // 登录成功
        if (checkResult != null){
            return checkResult;
        }
        return null;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 20:18
     * @description 获取rsa密钥对中的公钥
     */
    @RequestMapping(value = "/getPublicKey")
    @ResponseBody
    public ResultModel getPublicKey(String type){
        Map<String, String> keys = RsaUtils.getKey();
        String token = UUID.randomUUID().toString();
        ResultModel resultModel = new ResultModel(keys.get("public"), token);
        // 存入Redis中
        RedisString redisString = RedisString.getInstans();
        String rsaPrivateAndPublic = keys.get("private")+"#"+keys.get("public");
        redisString.setData(token, rsaPrivateAndPublic, -1);

        return resultModel;
    }

    @RequestMapping(value = "/getVerityCode")
    @ResponseBody
    public ResultModel getVerityCode(String token,String email){
        RedisString redisString = RedisString.getInstans();
        String value = redisString.getValueByKey(token);
        if (StrUtil.isNotEmpty(value)){

            Integer verityCode = EmailUtils.sendVerifyCode(email);
            ResultModel resultModel = new ResultModel(verityCode.toString(), token);

            return resultModel;
        }else {
            ResultModel resultModel = new ResultModel("获取验证码失败");
            return resultModel;
        }
    }

    public ResultModel registerUser(){
        return null;
    }

}
