package com.huzheng.commoms.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.Set;

/**
 * @Author 胡正
 * @Date 2020/3/16 17:56
 * @Description mybatis-plus  Wrapper工具类
 */
public class WrapperUtils<T>{

    public Wrapper queryWrapper(T t){
        Map<String, Object> beanMap = BeanUtil.beanToMap(t);
        Set<String> keys= beanMap.keySet();
        QueryWrapper wrapper = new QueryWrapper();
        for (String column : keys) {
            wrapper.eq(column,beanMap.get(column));
        }
        return wrapper;
    }
}
