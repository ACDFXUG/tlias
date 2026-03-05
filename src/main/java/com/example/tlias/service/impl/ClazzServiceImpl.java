package com.example.tlias.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tlias.mapper.ClazzMapper;
import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam cqp) {
        PageHelper.startPage(cqp.getPage(),cqp.getPageSize());

        List<Clazz> clazzPage=clazzMapper.list(cqp);
        @SuppressWarnings("resource")
        Page<Clazz> page=(Page<Clazz>)clazzPage;
        return new PageResult<>(page.getTotal(),page.getResult());
    }
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
    @Override
    public List<Clazz> list() {
        return clazzMapper.findAll();
    }
    
}
