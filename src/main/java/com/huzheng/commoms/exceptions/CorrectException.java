package com.huzheng.commoms.exceptions;

/**
 * @Author 胡正
 * @Date 2020/3/25 17:57
 * @Description 自定义异常，主要用于主动抛出异常，可预见的
 */
public class CorrectException extends RuntimeException{

    private String message;

    public CorrectException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
