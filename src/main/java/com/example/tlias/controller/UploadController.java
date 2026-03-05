package com.example.tlias.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.tlias.pojo.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UploadController {
    
    @PostMapping("/upload")
    public Result upload(String name,Integer age,MultipartFile file) throws IllegalStateException, IOException{
        log.info("文件上传:{}",file);
        String originName=file.getOriginalFilename();
        String fileName=UUID.randomUUID().toString()+originName.substring(originName.lastIndexOf("."));
        file.transferTo(Path.of("C:\\Users\\yaoyu\\Desktop\\Maven\\tlias",fileName));
        return Result.success();
    }
}
