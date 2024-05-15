package com.albedo.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public class Category {
    /*
     @NotNull:不能不传
     @NotEmpty:不能不传，并且如果是字符串时，不能传null
     */
    private Integer id;//主键ID
    @NotEmpty//非空校验
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
