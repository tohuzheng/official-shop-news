package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Login;
import org.springframework.stereotype.Repository;

/**
 * @Author 胡正
 * @Date 2020/2/6 12:43
 * @Description 登录数据库操作接口
 */
public interface LoginDao extends BaseMapper<Login> {

    Login checkLogin(Login login);

}
