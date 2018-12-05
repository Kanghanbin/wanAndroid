package com.kanghanbin.wanandroid.http;

/**
 * 创建时间：2018/10/29
 * 编写人：kanghb
 * 功能描述：
 */
public class ApiException extends Exception {
    private int code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
