package com.example.tlias.service;

import java.util.List;

import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam sqp);

    void delete(List<Integer> ids);

    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Short score);
    
}
