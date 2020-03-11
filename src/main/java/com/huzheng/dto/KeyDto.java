package com.huzheng.dto;

/**
 * @Author 胡正
 * @Date 2020/3/11 12:46
 * @Description 获取密钥对Dto
 */
public class KeyDto {

    private String publicKey;
    private String keyNo;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }
}
