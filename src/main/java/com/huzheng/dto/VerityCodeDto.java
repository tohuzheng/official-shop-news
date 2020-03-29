package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/29 20:05
 * @Description
 */
public class VerityCodeDto {
    /**
     * 验证码keyNo
     */
    private String verityCodeKeyNo;
    /**
     * 验证码
     */
    private String verityCode;

    public String getVerityCodeKeyNo() {
        return verityCodeKeyNo;
    }

    public void setVerityCodeKeyNo(String verityCodeKeyNo) {
        this.verityCodeKeyNo = verityCodeKeyNo;
    }

    public String getVerityCode() {
        return verityCode;
    }

    public void setVerityCode(String verityCode) {
        this.verityCode = verityCode;
    }
}
