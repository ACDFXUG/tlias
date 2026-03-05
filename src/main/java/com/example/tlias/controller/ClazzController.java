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

import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.ClazzService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam cqp){
        log.info("分页查询班级");
        PageResult<Clazz> clazzPage=clazzService.page(cqp);
        return Result.success(clazzPage);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级数据"+id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级数据"+clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级数据"+id);
        Clazz clazz=clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级数据"+clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级数据");
        List<Clazz> clazzList=clazzService.list();
        return Result.success(clazzList);
    }
}
