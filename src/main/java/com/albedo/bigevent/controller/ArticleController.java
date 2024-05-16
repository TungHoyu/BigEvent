package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Article;
import com.albedo.bigevent.pojo.PageBean;
import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.service.ArticleService;
import com.albedo.bigevent.utils.JwtUtil;
import org.apache.ibatis.annotations.Delete;
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

   @GetMapping
   public Result<PageBean<Article>> list(Integer pageNum,
                                         Integer pageSize,
                                         @RequestParam(required = false) Integer categoryId,
                                         @RequestParam(required = false)String state){
       PageBean pb = articleService.list(pageNum,pageSize,categoryId,state);
       return Result.success(pb);
   }

   @PutMapping
   public Result<Article> update(@RequestBody Article article){
       articleService.update(article);
       return Result.success();
   }

   @GetMapping("/detail")
   public Result<Article> detail(Integer id){
       Article a = articleService.findById(id);
       return Result.success(a);
   }

   @DeleteMapping
   public Result delete(Integer id){
       articleService.delete(id);
       return Result.success();
   }
}
