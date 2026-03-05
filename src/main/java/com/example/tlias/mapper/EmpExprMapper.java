package com.example.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.tlias.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> empIds);

}
