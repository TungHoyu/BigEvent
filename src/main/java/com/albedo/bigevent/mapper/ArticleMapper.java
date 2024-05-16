package com.albedo.bigevent.mapper;

import com.albedo.bigevent.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //条件分页查询文章列表
    List<Article> list(Integer userId, Integer categoryId, String state);

    //更新文章
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId} where id=#{id}")
    void update(Article article);

    //根据id查询文章详细信息
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    //根据id删除文章
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
