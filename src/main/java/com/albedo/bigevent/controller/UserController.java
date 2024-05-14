package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.pojo.User;
import com.albedo.bigevent.service.UserService;
import com.albedo.bigevent.utils.JwtUtil;
import com.albedo.bigevent.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
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

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$")String password){
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断该用户是否存在
        if (loginUser == null){
            return Result.error("用户名错误！");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            //登录成功
            return Result.success(token);
        }
        //判断密码是否正确
        return Result.error("密码错误！");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
