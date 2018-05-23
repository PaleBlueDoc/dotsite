package com.dotsite.modules.conf.exception;

/**
 * User: QNS
 * Description: 自定义错误异常类，返回错误JSON数据
 * Data: 2017/9/30 17:20
 **/
public class RespException extends Exception {

    public RespException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
