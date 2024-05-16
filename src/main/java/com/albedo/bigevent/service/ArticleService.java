package com.albedo.bigevent.service;

import com.albedo.bigevent.pojo.Article;
import com.albedo.bigevent.pojo.PageBean;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void add(Article article);

    /**
     * 条件分页列表查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    /**
     * 更新文章
     * @param article
     */
    void update(Article article);

    /**
     * 根据id查询文章详细信息
     * @param id
     * @return
     */
    Article findById(Integer id);

    /**
     * 根据id删除文章
     * @param id
     */
    void delete(Integer id);
}
