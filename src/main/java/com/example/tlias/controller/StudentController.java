package com.example.tlias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Result;
import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;
import com.example.tlias.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(StudentQueryParam sqp){
        log.info("分页查询: {}",sqp);
        PageResult<Student> pageRes=studentService.page(sqp);
        return Result.success(pageRes);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工:{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加员工:{}",student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){ 
        log.info("查询员工信息:{}",id);
        Student student=studentService.getInfo(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新员工信息:{}",student);
        studentService.update(student);
        return Result.success(); 
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,@PathVariable Short score){
        log.info("更新员工违规信息:{}",id); 
        studentService.updateViolation(id,score);
        return Result.success();
    }
}
