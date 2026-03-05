package com.example.tlias.service;

import java.util.List;

// import java.time.LocalDate;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpQueryParam;
import com.example.tlias.pojo.LoginInfo;
import com.example.tlias.pojo.PageResult;

public interface EmpService {

    // PageResult<Emp> page(
    //     Integer page, Integer pageSize,String name,
    //     Integer gender,LocalDate begin,LocalDate end
    // );

    PageResult<Emp> page(EmpQueryParam eqp);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);

}
