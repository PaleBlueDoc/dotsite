/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.dotsite.modules;

import com.dotsite.common.io.PropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * dotsite web
 *
 * @author qns
 * @date 2018-05-16
 */
@SpringBootApplication(scanBasePackages = {"com.dotsite.modules"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(PropertiesUtils.getInstance().getProperties());
        app.run(args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        this.setRegisterErrorPageFilter(false); // 错误页面有容器来处理，而不是SpringBoot
        builder.properties(PropertiesUtils.getInstance().getProperties());
        return builder.sources(Application.class);
    }

}