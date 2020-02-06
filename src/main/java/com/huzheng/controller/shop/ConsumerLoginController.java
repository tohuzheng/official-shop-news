package com.huzheng.controller.shop;

import com.huzheng.entity.Login;
import com.huzheng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 胡正
 * @Date 2020/1/20 10:35
 * @Description 客户登录控制器
 */
@RequestMapping("/consumer")
public class ConsumerLoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/checkLong")
    @ResponseBody
    public Login checkLogin(Login login){
        Login checkResult=loginService.loginCheck(login);
        if (checkResult != null){
            return checkResult;
        }
        return null;
    }


}
