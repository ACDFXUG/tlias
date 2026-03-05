package com.example.tlias.controller;

import java.util.List;

// import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpQueryParam;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 分页查询员工信息
     * 处理员工分页查询请求，接收查询参数并返回分页结果
     *
     * @param eqp 员工查询参数对象，包含分页信息和查询条件
     * @return Result 包含分页数据的统一响应结果
     */
    @GetMapping
    public Result page(EmpQueryParam eqp){
        log.info("分页查询: {}",eqp);
        PageResult<Emp> pageRes=empService.page(eqp);
        return Result.success(pageRes);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(/*Integer[] ids*/@RequestParam List<Integer> ids){
        log.info("删除员工:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息:{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
