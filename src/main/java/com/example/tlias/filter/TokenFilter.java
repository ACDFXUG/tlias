package com.example.tlias.filter;

import java.io.IOException;

import com.example.tlias.utils.JWTUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
// import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @WebFilter
public class TokenFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsrq=(HttpServletRequest)request;
        HttpServletResponse hsrp=(HttpServletResponse)response;

        String url=hsrq.getRequestURI();

        if(url.contains("/login")){
            chain.doFilter(request,response);
            return;
        }

        String token=hsrq.getHeader("token");

        if(token==null||token.equals("")){
            log.info("token为空");
            hsrp.setStatus(401);
            return;
        }

        try{
            JWTUtils.parseToken(token);
        }catch(Exception e){
            log.info("token无效");
            hsrp.setStatus(401);
            return;
        }
        
        log.info("token有效");
        chain.doFilter(request,response);
    }
    
}
