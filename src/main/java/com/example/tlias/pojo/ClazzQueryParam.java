package com.example.tlias.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ClazzQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate end;
}
