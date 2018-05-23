package com.dotsite.modules.conf.exception;

import com.dotsite.common.dto.RespBean;
import com.dotsite.common.mapper.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: QNS
 * Description: 异常统一处理类
 * Data: 2017/9/30 17:22
 **/
@ControllerAdvice
public class ExceptionHandlerManage {

    Logger logger = LoggerFactory.getLogger(ExceptionHandlerManage.class);

    @ExceptionHandler(RespException.class)
    @ResponseBody
    public String respExceptionHandler(RespException e) {
        logger.error("返回异常", e);
        JsonMapper.toJson(new RespBean<>(e));
        return "";
    }

    @ExceptionHandler(RespPageException.class)
    public String respPageExceptionHandler(RespPageException e, Model model) {
        logger.error("发生异常", e);
        model.addAttribute("errMap", new RespBean<>(e));
        return "error/error";
    }


    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        logger.error("发生异常", e);
        model.addAttribute("errMap", new RespBean<>(e));
        return "error/error";
    }


}
