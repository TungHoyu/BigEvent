package com.albedo.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
@Data
public class Category {
    /*
     @NotNull:不能不传
     @NotEmpty:不能不传，并且如果是字符串时，不能传null
     */
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty//非空校验
    private String categoryName;//分类名称
    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;//更新时间


    //如果某个校验项没有指定分组，默认属于Default分组
    //分组之间可以继承，A extends B，那么A中拥有B的所有校验项
    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
