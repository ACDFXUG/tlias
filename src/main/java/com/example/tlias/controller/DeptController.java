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
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlias.anno.Log;
import com.example.tlias.pojo.Dept;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.DeptService;

@RequestMapping("/depts") // 公共路径抽取
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // @RequestMapping(value="/depts",method=RequestMethod.GET)
    @GetMapping
    @Log
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> depts=deptService.findAll();
        return Result.success(depts);
    }

    // @DeleteMapping("/depts")
    // public Result delete(@RequestParam("id") Integer rqstID){
    //     System.out.println("删除部门数据"+rqstID);
    //     return Result.success(); 
    // }

    @DeleteMapping
    public Result delete(Integer id){
        System.out.println("删除部门数据"+id);
        deptService.deleteById(id);
        return Result.success(); 
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部门数据"+dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){ 
        System.out.println("查询部门数据"+id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){ 
        System.out.println("修改部门数据"+dept);
        deptService.update(dept);
        return Result.success();
    }
}
