package com.huzheng.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huzheng.commoms.utils.*;
import com.huzheng.dto.*;
import com.huzheng.entity.Customer;
import com.huzheng.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public ResultModel checkLogin(LoginDto loginDto, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        ResultModel resultModel =new ResultModel();
        String ciphertext = loginDto.getPassword();
        // 从Redis中获取私钥
        Jedis jedis = RedisDS.create().getJedis();
        String privateKey = jedis.get(loginDto.getKeyNo());

        try {
            // 解密
            String password1 = RsaUtils.decodeRsa(ciphertext, privateKey);
            loginDto.setPassword(password1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer checkResult=customerService.checkLogin(loginDto);

        if (checkResult != null){
            // 登录成功
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",checkResult);
            // 添加token，存入cookie
            String token = LoginUtils.saveToken(request);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            // 使用完毕，从redis中删除私钥
            jedis.del(loginDto.getKeyNo());
            jedis.close();
            resultModel.setMsg("ok");
            resultModel.setToken(UUID.randomUUID().toString());
            checkResult.setPassword("");
            resultModel.setDto(checkResult);
            return resultModel;
        }else {
            // 登录失败
            resultModel.setMsg("用户名或者密码错误");
            resultModel.setCode("401");
            return resultModel;
        }

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
        String rsaPrivate = keys.get("private");
        Jedis jedis = RedisDS.create().getJedis();
        jedis.set(keyNo, rsaPrivate);
        jedis.close();

        return keyDto;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 19:48
     * @description 注册时获取邮箱验证码,返回验证码和验证码keyNo
     */
    @RequestMapping(value = "/getVerityCode")
    @ResponseBody
    public ResultModel getVerityCode(String email){
        ResultModel resultModel = new ResultModel();
        if (StrUtil.isNotEmpty(email)){
            Integer verityCode = EmailUtils.sendVerifyCode(email);
            String verityCodeKey = UUID.randomUUID().toString();
            // 把验证码存入Redis设置1小时过期
            Jedis jedis = RedisDS.create().getJedis();
            jedis.setex(verityCodeKey,60*60,verityCode+"");
            jedis.close();
            // 把验证码的key发到前端
            VerityCodeDto dto =new VerityCodeDto();
            //dto.setVerityCode(verityCode+"");
            dto.setVerityCodeKeyNo(verityCodeKey);
            resultModel.setDto(dto);
            return resultModel;
        }else {
            resultModel.setMsg("获取验证码失败");
            resultModel.setCode("401");
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
    public ResultModel registerUser(Customer customer, String keyNo, String verityCodeKey,String verityCode){
        // 获取验证码,获的密钥
        Jedis jedis = RedisDS.create().getJedis();
        String privateKey = jedis.get(keyNo);
        String verity = jedis.get(verityCodeKey);

        if (StrUtil.isEmpty(verity) || StrUtil.isEmpty(verityCode)) {
            return new ResultModel("验证码错误");
        }
        if (!verityCode.equals(verity)) {
            return new ResultModel("验证码错误");
        }

        try {
            // 解密
            String password1 = RsaUtils.decodeRsa(customer.getPassword(), privateKey);
            customer.setPassword(password1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 添加顾客
        customerService.insert(customer);
        // 添加成功，删除密钥
        jedis.del(keyNo);
        jedis.del(verityCodeKey);
        jedis.close();
        return new ResultModel("ok");
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

    /**
     * @author zheng.hu
     * @date 2020/3/12 9:45
     * @description 分页查询顾客信息，默认10条每页
     */
    @RequestMapping(value = "/queryAllByPage")
    @ResponseBody
    public Page<Customer> queryAllByPage(QueryCustomerDto queryCustomerDto){
        return customerService.queryAllByLimit(queryCustomerDto);
    }


    /**
     * @author zheng.hu
     * @date 2020/3/16 23:13
     * @description 重置用户密码
     */
    @RequestMapping(value = "/resetPass")
    @ResponseBody
    public void resetPass(Integer id){
        if (id == null){
            throw new RuntimeException("id为空，参数错误");
        }
        customerService.resetPass(id);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/16 23:13
     * @description 冻结用户或解冻
     */
    @RequestMapping(value = "/freezeCustomer")
    @ResponseBody
    public void freezeCustomer(Integer id,Integer status){

        customerService.freezeCustomer(id, status);
    }

    /**
     * @author zheng.hu
     * @date 2020/3/30 22:26
     * @description 获取用户名
     * @param request
     */
    @GetMapping("/getUserName")
    @ResponseBody
    public String getUserName (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer userInfo = (Customer) session.getAttribute("userInfo");
        return userInfo.getUsername();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 19:20
     * @description 退出登录
     * @param request
     */
    @GetMapping("/outLogin")
    @ResponseBody
    public void outLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 21:44
     * @description 获取用户个人信息
     * @param
     */
    @GetMapping("/getPersonInfo")
    @ResponseBody
    public Customer getPersonInfo(HttpServletRequest request) {
        return (Customer) request.getSession().getAttribute("userInfo");
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updatePersonInfo")
    @ResponseBody
    public void updatePersonInfo(Customer customer) {
        customerService._updateById(customer);
    }

    /**
     * @author zheng.hu
     * @date 2020/4/19 21:53
     * @description 修改密码
     * @param dto
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public void updatePassword(UpdatePasswordDto dto) {
        customerService.updatePassword(dto);
    }
}
