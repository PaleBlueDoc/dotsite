package com.dotsite.modules.conf.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: QNS
 * @Description: check session has login user
 * @Data: 2017/11/22 14:48
 **/
public class ResponseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // iframe 嵌套页面时设置规则，相同域名的页面可以进行嵌套，防止广告植入（应该是springsecurity的设置）
        httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
