package com.example.tlias.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tlias.mapper.OperateLogMapper;
import com.example.tlias.pojo.OperateLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @SuppressWarnings("unused")
    @Autowired
    private OperateLogMapper operateLogMapper;

    private Integer getCurrentEmpId(){
        return 1;
    }
    
    @Around("@annotation(com.example.tlias.anno.Log)")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable { 
        long start=System.currentTimeMillis();
        Object res=pjp.proceed();
        long end=System.currentTimeMillis();

        OperateLog ol=new OperateLog();
        ol.setOperateEmpId(getCurrentEmpId());
        ol.setOperateTime(LocalDateTime.now());
        ol.setClassName(pjp.getTarget().getClass().getName());
        ol.setMethodName(pjp.getSignature().getName());
        ol.setMethodParams(Arrays.toString(pjp.getArgs()));
        ol.setReturnValue(res==null?"void":res.toString());
        ol.setCostTime(end-start);

        log.info("记录日志:{}",ol);

        return res;
    }
}
