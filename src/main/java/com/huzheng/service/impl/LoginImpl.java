package com.huzheng.service.impl;

import com.huzheng.dao.LoginMapper;
import com.huzheng.entity.Login;
import com.huzheng.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 胡正
 * @Date 2020/2/6 12:55
 * @Description
 */
@Service
public class LoginImpl implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login loginCheck(Login login) {
        if (login == null){
            throw new RuntimeException("传入登录对象为null，请传入正确参数");
        }
        return loginMapper.checkLogin(login);
    }
}
