package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Article;
import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.service.ArticleService;
import com.albedo.bigevent.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

   @PostMapping
    public Result add(@RequestBody @Validated Article article){
       articleService.add(article);
       return Result.success();
   }
}
