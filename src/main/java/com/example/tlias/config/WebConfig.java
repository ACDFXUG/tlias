package com.example.tlias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.tlias.interceptor.TliasInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TliasInterceptor tliasInterceptor;
    
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tliasInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/login");
    } 

}
