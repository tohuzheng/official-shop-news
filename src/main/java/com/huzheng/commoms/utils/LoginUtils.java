package com.huzheng.commoms.utils;

import cn.hutool.crypto.digest.DigestUtil;
import com.huzheng.entity.Customer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/4/21 13:53
 * @Description 登录工具类
 */
public class LoginUtils {

    /**
     * @author zheng.hu
     * @date 2020/4/21 13:54
     * @description 生成token并储存
     * @param request
     * @return 返回存贮的token值
     */
    public static String saveToken(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer=null;
        if (userInfo instanceof Customer) {
            customer = (Customer) userInfo;
        }
        String ip = IpUtils.getRequestIP(request);
        String ipMD5 = DigestUtil.md5Hex(ip);
        return ipMD5;
    }

    /**
     * @author zheng.hu
     * @date 2020/4/21 14:26
     * @description 检查token
     * @param request
     */
    public static boolean checkToken(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        Customer customer=null;
        if (userInfo instanceof Customer) {
            customer = (Customer) userInfo;
        }
        Cookie[] cookies = request.getCookies();
        Map<String,String> map = new HashMap<>();
        for (Cookie cookie : cookies) {
            map.put(cookie.getName(),cookie.getValue());
        }
        String ip = IpUtils.getRequestIP(request);
        String ipMD5 = DigestUtil.md5Hex(ip);
        String token = map.get("token");
        return token.equals(ipMD5);
    }
}
