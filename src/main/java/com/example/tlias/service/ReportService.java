package com.example.tlias.service;

import java.util.List;
import java.util.Map;

import com.example.tlias.pojo.ClazzCountOption;
import com.example.tlias.pojo.JobOption;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzCountOption getStudentCountData();

}
