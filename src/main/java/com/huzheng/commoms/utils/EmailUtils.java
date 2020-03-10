package com.huzheng.commoms.utils;

import cn.hutool.extra.mail.MailUtil;


/**
 * @Author 胡正
 * @Date 2020/3/10 14:31
 * @Description 邮件工具类，主要用于发送邮件
 */
public class EmailUtils {

    /**
     * @author zheng.hu
     * @date 2020/3/10 15:24
     * @description 发送验证码
     * @param recipients 收件人
     * @return code 验证码
     */
    public static Integer sendVerifyCode(String recipients){
        // 邮件主题
        String title = "企业产品在线展示销售平台--注册验证";
        // 邮件内容前缀
        String contextPrefix = "<h3>亲爱的用户</h3><p>您好！感谢您使用企业产品在线展示销售平台，您注册账户验证码为：<span style=\"color:red;\">";
        // 邮件内容后缀
        String contextSuffix = "</span><br>一小时后过期，请注意时间喔！</p>";
        // 验证码
        Integer code = (int)((Math.random()*9+1)*100000);
        String context = contextPrefix + code + contextSuffix;
        // 发送邮件
        MailUtil.send(recipients, title, context,true);
        return code;
    }


    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        sendVerifyCode("1437072425@qq.com");
        Long over = System.currentTimeMillis();
        // 总耗时：5758毫秒
        System.out.println(String.format("总耗时：%d毫秒", (over-start)));
    }
}
