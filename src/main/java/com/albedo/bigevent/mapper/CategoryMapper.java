package com.albedo.bigevent.mapper;

import com.albedo.bigevent.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    /**
     * 查询所有
     * @param userId
     * @return
     */
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
    @Select("select * from category where id =#{id}")
    Category findById(Integer id);

    /**
     * 更新分类
     * @param category
     */
    @Update("update category set category_name =#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id =#{id}")
    void update(Category category);

    /**
     * 根据id删除文章分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);
}
