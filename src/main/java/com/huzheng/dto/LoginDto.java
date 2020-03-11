package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/11 12:55
 * @Description 登录接受参数Dto
 */
public class LoginDto {
    private String keyNo;
    private String username;
    private String password;

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
