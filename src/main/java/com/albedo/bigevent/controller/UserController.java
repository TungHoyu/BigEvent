package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.pojo.User;
import com.albedo.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password){
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null){
            //注册
            userService.register(username,password);
            return Result.success();
        }else{
            //占用
            return Result.error("该用户已存在！");
        }
    }
}
