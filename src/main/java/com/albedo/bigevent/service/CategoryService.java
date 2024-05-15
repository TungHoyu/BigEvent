package com.albedo.bigevent.service;

import com.albedo.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void add(Category category);

    /**
     * 查询文章列表
     * @return
     */
    List<Category> list();

    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
    Category findById(Integer id);

    /**
     * 更新分类
     * @param category
     */
    void update(Category category);

    /**
     * 根据id删除文章分类
     * @param id
     */
    void deleteById(Integer id);
}
