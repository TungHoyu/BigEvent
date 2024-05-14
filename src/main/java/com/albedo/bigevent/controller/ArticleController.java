package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token,HttpServletResponse response*/){
        //验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有文章数据。。。。");
//        } catch (Exception e) {
//            //http响应状态码为401
//            response.setStatus(401);
//            return Result.error("未登录！");
//        }
        return Result.success("所有文章数据。。。。");

    }
}
