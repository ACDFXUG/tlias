package com.example.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;

@Mapper
public interface ClazzMapper {
    public List<Clazz> list(ClazzQueryParam cqp);

    @Delete("delete from clazz where id=#{id}")
    public void deleteById(Integer id);

    public void add(Clazz clazz);

    @Select("select * from clazz where id=#{id}")
    public Clazz getById(Integer id);

    public void update(Clazz clazz);

    @Select("select * from clazz")
    public List<Clazz> findAll();
    
}
