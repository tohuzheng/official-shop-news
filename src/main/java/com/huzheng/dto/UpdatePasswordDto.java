package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/4/19 21:55
 * @Description 修改密码dto
 */
public class UpdatePasswordDto {
    // 用户id
    private Integer customerId;
    // 用户名
    private String username;
    // 老密码
    private String passwordOld;
    // 新密码
    private String passwordNew;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}
