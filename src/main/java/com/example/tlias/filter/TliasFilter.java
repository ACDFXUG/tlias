package com.example.tlias.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
// import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @WebFilter(urlPatterns="/*")
public class TliasFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TliasFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("TliasFilter doFilter...");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("TliasFilter destroy...");
    }
    
}
