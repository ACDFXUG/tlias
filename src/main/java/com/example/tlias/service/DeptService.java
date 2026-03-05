package com.example.tlias.service;

import java.util.List;

import com.example.tlias.pojo.Dept;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> findAll();
    /**
     * 根据id删除部门
     * @param id
     */
    void deleteById(Integer id);
    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);
    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);
    void update(Dept dept);
    
}
