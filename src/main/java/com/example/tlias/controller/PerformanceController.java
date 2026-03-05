package com.example.tlias.controller;

import com.example.tlias.pojo.EmpQueryParam;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.DeptService;
import com.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/perf")
public class PerformanceController {
    
    @Autowired
    private DeptService deptService;
    
    private final ExecutorService executorService = Executors.newFixedThreadPool(20);
    
    @GetMapping("/qps-test")
    public Result testQPS(@RequestParam(defaultValue = "100") int requests,
                          @RequestParam(defaultValue = "10") int threads) {
        long startTime = System.currentTimeMillis();
        
        CompletableFuture<?>[] futures = new CompletableFuture[requests];
        
        for (int i = 0; i < requests; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
                // 执行一个简单的数据库查询
                deptService.findAll();
            }, executorService);
        }
        
        CompletableFuture.allOf(futures).join();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double qps = (double) requests / (duration / 1000.0);
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "QPS测试完成");
        result.put("requests", requests);
        result.put("duration_ms", duration);
        result.put("qps", qps);
        
        return Result.success(result);
    }
    
    @GetMapping("/simple-db-query")
    public Result simpleDbQuery(@RequestParam(defaultValue = "1") Integer id) {
        // 简单的数据库查询，用于测试
        long startTime = System.nanoTime();
        var result = deptService.getById(id);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000; // 微秒
        
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "简单查询完成，耗时: " + duration + " 微秒");
        resultMap.put("data", result);
        
        return Result.success(resultMap);
    }
}