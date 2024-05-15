package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.pojo.User;
import com.albedo.bigevent.service.UserService;
import com.albedo.bigevent.utils.JwtUtil;
import com.albedo.bigevent.utils.Md5Util;
import com.albedo.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    /**
     * 注册
     * @param username
     * @param password
     * @return Result
     */
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

    /**
     * 登录
     * @param username
     * @param password
     * @return Result
     */
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

    /**
     * 获取用户详细信息
     * @return Result
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户基本信息
     * @param user
     * @return Result
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     * @return Result
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 更新密码
     * @param params
     * @return Result
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1. 校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        //原密码是否正确
        //调用userservice，根据用户名拿到原密码，再和oldPwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确！");
        }
        //newPwd与rePwd是否一致
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一致！");
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(newPwd))){
            return Result.error("新密码不能与旧密码相同！");
        }
        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
