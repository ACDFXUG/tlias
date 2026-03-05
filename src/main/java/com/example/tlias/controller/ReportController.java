package com.example.tlias.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias.pojo.ClazzCountOption;
import com.example.tlias.pojo.JobOption;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工人数");
        JobOption jo=reportService.getEmpJobData();
        return Result.success(jo);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> list=reportService.getEmpGenderData();
        return Result.success(list);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历人数");
        List<Map<String,Object>> list=reportService.getStudentDegreeData();
        return Result.success(list);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计学生人数");
        ClazzCountOption cco=reportService.getStudentCountData();
        return Result.success(cco);
    }
}
