package com.huzheng.service.impl;

import com.huzheng.dao.LoginDao;
import com.huzheng.entity.Login;
import com.huzheng.service.ILoginService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 胡正
 * @Date 2020/2/6 12:55
 * @Description
 */
@Service
public class LoginImpl extends IBaseServiceImpl<LoginDao,Login> implements ILoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Login loginCheck(Login login) {
        if (login == null){
            throw new RuntimeException("传入登录对象为null，请传入正确参数");
        }
        return loginDao.checkLogin(login);
    }
}
