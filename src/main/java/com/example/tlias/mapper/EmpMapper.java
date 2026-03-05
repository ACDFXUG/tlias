package com.example.tlias.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Select;
// import org.springframework.format.annotation.DateTimeFormat;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     * @return
     */
    // @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    // public Long count();
    /**
     * 分页查询
     * @return
     */
    // @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id "+
    //     "order by e.update_time desc limit #{start},#{pageSize}")
    // public List<Emp> list(Integer start,Integer pageSize);

    /**
     * pagehelper
     * 句尾不能加分号
     * 只能在调用的后的第一个SQL语句进行分页查询
     */
    // @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    // public List<Emp> list(String name,Integer gender,LocalDate begin,LocalDate end);

    public List<Emp> list(EmpQueryParam eqp);

    @Options(useGeneratedKeys=true,keyProperty="id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)"
        +"values(#{userName},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})"
    )
    public void insert(Emp emp);

    public void deleteByIds(List<Integer> ids);

    public Emp getById(Integer id);

    public void updateById(Emp emp);

    public List<Map<String,Object>> countEmpJobData();

    public List<Map<String,Object>> countEmpGenderData();

    @Select("select * from emp where username=#{username} and password=#{password}")
    public Emp getByUsernameAndPassword(String username, String password);
}
