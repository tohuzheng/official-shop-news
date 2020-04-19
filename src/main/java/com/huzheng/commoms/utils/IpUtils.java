package com.huzheng.commoms.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 胡正
 * @Date 2020/4/19 11:10
 * @Description 获取ip工具类
 */
public class IpUtils {

    public static String getRequestIP(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
