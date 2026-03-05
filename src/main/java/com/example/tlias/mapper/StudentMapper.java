package com.example.tlias.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam sqp);

    void delete(List<Integer> ids);

    void add(Student student);

    @Select("select * from student where id=#{id}")
    Student getInfo(Integer id);

    void update(Student student);

    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void updateViolation(Integer id, Short score);

    List<Map<String, Object>> countStudentDegreeData();

    List<Map<String, Object>> countStudentClazzData();
    
}
