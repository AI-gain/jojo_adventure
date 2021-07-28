package com.jojo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// @Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")//所有应用都会去处理跨域请求
//                .allowedHeaders("*")//允许所有请求头
//                .allowedMethods("*")//请求通过的请求数
//                .allowedOrigins("*")//请求地址
//                .allowCredentials(true);//允许证书
//    }
}
