package com.example.tlias.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.mapper.StudentMapper;
import com.example.tlias.pojo.ClazzCountOption;
import com.example.tlias.pojo.JobOption;
import com.example.tlias.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        var list=empMapper.countEmpJobData();
        List<Object> jobList=list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList=list.stream().map(dataMap->dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }
    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> list=studentMapper.countStudentClazzData();
        var clazzs=list.stream().map(dataMap->dataMap.get("name")).toList();
        var counts=list.stream().map(dataMap->dataMap.get("value")).toList();
        return new ClazzCountOption(clazzs, counts);
    }
    
}
