package com.example.tlias.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TliasInterceptor implements HandlerInterceptor{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的url
        String url = request.getRequestURI();
        // 获取请求的方法
        String method = request.getMethod();
        // 获取请求的参数
        Map<String, String[]> params = request.getParameterMap();
        log.info("请求开始，地址：{},方法：{},参数：{}", url, method, params);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("处理结束，地址：{},返回值：{}", request.getRequestURI(), modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 获取请求的url
        String url = request.getRequestURI();
        // 获取请求的方法
        String method = request.getMethod();
        log.info("请求结束，地址：{},方法：{}", url, method);
    }
}
