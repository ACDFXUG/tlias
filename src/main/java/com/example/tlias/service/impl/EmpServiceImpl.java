package com.example.tlias.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// import java.time.LocalDate;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.tlias.mapper.EmpExprMapper;
import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpLog;
import com.example.tlias.pojo.EmpQueryParam;
import com.example.tlias.pojo.LoginInfo;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.service.EmpLogService;
import com.example.tlias.service.EmpService;
import com.example.tlias.utils.JWTUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    // @Override
    // public PageResult<Emp> page(Integer page, Integer pageSize,
    //     String name,Integer gender,LocalDate begin,LocalDate end
    // ) {
    //     // Long count=empMapper.count();
    //     // List<Emp> rows=empMapper.list((page-1)*pageSize, pageSize);

    //     PageHelper.startPage(page,pageSize);
    //     var list=empMapper.list(name,gender,begin,end);
    //     @SuppressWarnings("resource")
    //     Page<Emp> pg=(Page<Emp>)list;

    //     return new PageResult<>(pg.getTotal(),pg.getResult());
    // }
    @Override
    public PageResult<Emp> page(EmpQueryParam eqp) {
        PageHelper.startPage(eqp.getPage(),eqp.getPageSize());
        var list=empMapper.list(eqp);

        @SuppressWarnings("resource")
        Page<Emp> pg=(Page<Emp>)list;

        return new PageResult<>(pg.getTotal(),pg.getResult());
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED) //推荐加在多次数据库操作的方法上,默认在RuntimeException下回滚,其他异常下不回滚
    public void save(Emp emp) {
        try{
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            var exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(expr->expr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        }finally{
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工: "+emp);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void delete(List<Integer> ids) {
        // 根据id批量删除员工
        empMapper.deleteByIds(ids);
        // 根据EmpId批量删除员工Expr
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        Integer id=emp.getId();
        empExprMapper.deleteByEmpIds(List.of(id));
        var exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->expr.setEmpId(id));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e=empMapper.getByUsernameAndPassword(emp.getUsername(),emp.getPassword());
        if(e!=null){
            String token=JWTUtils.generateToken(Map.of(
                "id",e.getId(),
                "username",e.getUsername()
            ));
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),token);
        }
        return null;
    }
}
