package com.huzheng.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.huzheng.commoms.utils.ResultModel;
import com.huzheng.entity.Login;
import com.huzheng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author 胡正
 * @Date 2020/1/23 17:24
 * @Description 登管理员录控制器/admin/loginCheck
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/loginCheck")
    @ResponseBody
    public ResultModel loginCheck(Login login, HttpServletRequest request){
        // 验证结果
        ResultModel<Login> resultModel = new ResultModel<Login>();
        // 验证账户密码是否正确
        Login checkResult=loginService.loginCheck(login);
        // 验证通过返回信息
        if (checkResult != null){
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",checkResult);
            String token = UUID.randomUUID().toString();
            resultModel.setDto(checkResult);
            resultModel.setMsg("ok");
            resultModel.setToken(token);
            return resultModel;
        }else {
            resultModel.setMsg("error");
            return resultModel;
        }
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public ResultModel loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if (StrUtil.isNotEmpty(username)) {
            session.invalidate();
            return new ResultModel("ok");
        }else {
            return new ResultModel("error");
        }
    }
}
