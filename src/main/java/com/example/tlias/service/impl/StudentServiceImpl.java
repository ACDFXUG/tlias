package com.example.tlias.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias.mapper.StudentMapper;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;
import com.example.tlias.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam sqp) {
        PageHelper.startPage(sqp.getPage(),sqp.getPageSize());
        List<Student> list=studentMapper.list(sqp);
        @SuppressWarnings("resource")
        Page<Student> page=(Page<Student>)list;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Short score) {
        studentMapper.updateViolation(id,score);
    }
    
}
