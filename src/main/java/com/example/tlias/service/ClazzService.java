package com.example.tlias.service;

import java.util.List;

import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;
import com.example.tlias.pojo.PageResult;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam cqp);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
    
}
