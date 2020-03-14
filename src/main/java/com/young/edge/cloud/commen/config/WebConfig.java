package com.young.edge.cloud.commen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tornado Young
 * @date time 2020/3/2 22:06
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                //先拦截所有请求
                .addPathPatterns("/**")
                //放过静态资源请求和登录请求
                .excludePathPatterns("/assets/**","/login","/doLogin");
    }
}
