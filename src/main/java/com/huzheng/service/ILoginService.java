package com.huzheng.service;

import com.huzheng.entity.Login;
import com.huzheng.service.base.IBaseService;

/**
 * @Author 胡正
 * @Date 2020/1/22 23:32
 * @Description
 */
public interface ILoginService extends IBaseService<Login> {

    Login loginCheck(Login login);
}
