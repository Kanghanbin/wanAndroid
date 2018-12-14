package com.kanghanbin.wanandroid.http;

/**
 * 创建时间：2018/10/26
 * 编写人：kanghb
 * 功能描述：封装http响应格式
 */
public class BaseResponse<T> {

    /**
     * data : null
     * errorCode : -1
     * errorMsg : 账号密码不匹配！
     */

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
