package com.dotsite.modules.conf.exception;

/**
 * User: QNS
 * Description: 自定义错误异常类，跳转到错误页面时使用
 * Data: 2017/9/30 17:20
 **/
public class RespPageException extends Exception {

    public RespPageException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
