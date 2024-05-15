package com.albedo.bigevent.controller;

import com.albedo.bigevent.pojo.Category;
import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 添加文章分类
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }

    /**
     * 查询文章分类
     * @return
     */
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    /**
     * 根据id查询文章分类
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    /**
     * 更新文章分类
     * @param category
     * @return
     */
    @PutMapping
    public Result update(@RequestBody @Validated Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        if (!(id == null)) {
            categoryService.deleteById(id);
            return Result.success();
        }
        return Result.error("id不能为空");
    }
}
