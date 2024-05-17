package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.utils.AliOssUtil;
import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverXPointer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件内容存储到本地磁盘
        String originalFilename = file.getOriginalFilename();
        //保证文件名字唯一，防止文件覆盖
        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}
