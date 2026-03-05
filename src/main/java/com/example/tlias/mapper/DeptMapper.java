package com.example.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Result;
// import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tlias.pojo.Dept;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     * @return
     */
    // @Results({
    //     @Result(column="create_time",property="createTime"),
    //     @Result(column="update_time",property="updateTime")
    // })手动进行属性映射
    // @Select("select id,name,create_time createTime,update_time updateTime from dept") 起别名
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getById(Integer id);
    /**
     * 修改部门
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
    
}
