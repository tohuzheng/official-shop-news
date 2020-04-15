package com.huzheng.filter;

import com.huzheng.commoms.exceptions.CorrectException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 胡正
 * @Date 2020/3/25 18:03
 * @Description
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        CorrectException correctException = null;

        if (ex instanceof CorrectException) {
            // 是可预见异常
            correctException = (CorrectException) ex;

        }else {
            // 不是可预见异常，统一处理

            correctException = new CorrectException("系统错误");
        }
        PrintWriter writer = null;
        try {
            // 把可预见异常写入response对象
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.write(correctException.getMessage());
            // 再抛出异常
            throw correctException;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // write流不可关闭，不然前台没法发现异常
        return null;
    }
}
