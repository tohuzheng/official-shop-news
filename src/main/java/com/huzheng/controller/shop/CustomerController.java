package com.huzheng.controller.shop;

import cn.hutool.core.util.StrUtil;
import com.huzheng.commoms.redis.RedisString;
import com.huzheng.commoms.utils.EmailUtils;
import com.huzheng.commoms.utils.Page;
import com.huzheng.commoms.utils.ResultModel;
import com.huzheng.commoms.utils.RsaUtils;
import com.huzheng.dto.KeyDto;
import com.huzheng.dto.LoginDto;
import com.huzheng.entity.Customer;
import com.huzheng.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 胡正
 * @Date 2020/1/20 10:35
 * @Description 客户信息控制器
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    /**
     * @author zheng.hu
     * @date 2020/3/11 19:47
     * @description 客户登录检查
     */
    @RequestMapping(value = "/checkLogin")
    @ResponseBody
    public Customer checkLogin(LoginDto loginDto){

        String ciphertext = loginDto.getPassword();
        RedisString redisString = RedisString.getInstans();
        String privateKey = redisString.getValueByKey(loginDto.getKeyNo());
        try {
            // 解密
            String password1 = RsaUtils.decodeRsa(ciphertext, privateKey);
            loginDto.setPassword(password1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer checkResult=customerService.checkLogin(loginDto);
        // 登录成功
        if (checkResult != null){
            redisString.delete(loginDto.getKeyNo());
            return checkResult;
        }
        return null;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/10 20:18
     * @description 产生密钥对，获取rsa密钥对中的公钥
     */
    @RequestMapping(value = "/getPublicKey")
    @ResponseBody
    public KeyDto getPublicKey(){

        Map<String, String> keys = RsaUtils.getKey();
        String keyNo = UUID.randomUUID().toString();
        KeyDto keyDto = new KeyDto();
        keyDto.setKeyNo(keyNo);
        keyDto.setPublicKey(keys.get("public"));
        // 存入Redis中
        RedisString redisString = RedisString.getInstans();
        String rsaPrivate = keys.get("private");
        redisString.setData(keyNo, rsaPrivate, -1);

        return keyDto;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 19:48
     * @description 注册时获取邮箱验证码
     */
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

    /**
     * @author zheng.hu
     * @date 2020/3/11 19:48
     * @description 注册
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public void registerUser(Customer customer){
        customerService.insert(customer);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 20:03
     * @description 通过id修改客户信息
     */
    @RequestMapping(value = "/updateCustomer")
    @ResponseBody
    public void updateInfo(Customer customer){
        customerService.update(customer);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 20:03
     * @description 通过id删除客户信息
     */
    @RequestMapping(value = "/deleteCustomer")
    @ResponseBody
    public void deleteCustomer(Integer id){
        customerService.deleteById(id);
    }

    @RequestMapping(value = "/queryAllByPage")
    @ResponseBody
    public Page<Customer> queryAllByPage(Page page){
        return customerService.queryAllByLimit(page);
    }

}
