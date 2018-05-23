package com.dotsite.common.dto;

import java.io.Serializable;

public class RespBean<T> implements Serializable {
    public static final String SUCCESS = "00000";
    public static final String FAIL = "99999";

    private String code = SUCCESS;
    private String message = "SUCCESS";
    private T data;


    public RespBean() {
    }

    public RespBean(T data) {
        this.data = data;
    }

    public RespBean(Throwable e) {
        this.code = FAIL;
        this.message = e.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
