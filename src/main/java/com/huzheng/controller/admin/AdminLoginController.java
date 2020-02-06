package com.huzheng.controller.admin;

import com.huzheng.entity.Login;
import com.huzheng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 胡正
 * @Date 2020/1/23 17:24
 * @Description 登录Controller
 */
@Controller
@RequestMapping("/login")
public class AdminLoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(Login login){
        Login checkResult=loginService.loginCheck(login);
        if (checkResult != null){
            return "你好，就是这样的";
        }
        return null;
    }
}
