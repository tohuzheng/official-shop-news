package com.huzheng.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.asm.Type;
import org.springframework.cglib.transform.impl.InterceptFieldFilter;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/3/10 23:03
 * @Description 自定义拦截器
 */
public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("userInfo");

        if (username != null){
            return true;
        }

        // 清空返回流
        response.reset();
        // 获取返回写出流对象
        PrintWriter printWriter = response.getWriter();
        // 设置编码格式
        response.setCharacterEncoding("UTF-8");
        // 设置返回为json格式
        response.setContentType("application/json;charset=UTF-8");
        // 设置值
        Map<String,Object> map = new HashMap<>();
        map.put("statusCode",401);
        map.put("msg","goLogin");
        // json对象化写入
        printWriter.write(JSON.toJSONString(map));
        printWriter.flush();
        // 关闭流
        printWriter.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
