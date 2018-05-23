package com.dotsite.modules.conf.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GlobalConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链

        // set response header
        registry.addInterceptor(new ResponseInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
