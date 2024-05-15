package com.albedo.bigevent.anno;

import com.albedo.bigevent.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.*;

@Documented//元注解 --标识state注解抽取到帮助文档中
@Target({ElementType.FIELD})//元注解 作用在哪里 --字段属性上  类、属性、方法。。。。
@Retention(RetentionPolicy.RUNTIME)//元注解 标识state注解在那个节点保留  编译阶段、源码阶段、运行时阶段  （注解的生命周期）
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是【已发布】或【草稿】";

    //指定分组
    Class<?>[] groups() default {};

    //负载  获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
