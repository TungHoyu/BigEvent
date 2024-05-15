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
}
