package com.huzheng.commoms.utils;

import java.util.List;

/**
 * @Author 胡正
 * @Date 2020/3/10 14:02
 * @Description response 返回结果数据模型
 */
public class ResultModel {

    /**
     * 消息
     */
    private String msg;
    /**
     * 登录后唯一身份码
     */
    private String token;
    /**
     * 请求状态码
     */
    private String code = "200";
    /**
     * 数据集合
     */
    private List data;

    public ResultModel(){}

    public ResultModel(String msg){
        this.msg = msg;
    }

    public ResultModel(List data, String code){
        this.data = data;
        this.code = code;
    }

    public ResultModel(String msg, String token){
        this.msg = msg;
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
