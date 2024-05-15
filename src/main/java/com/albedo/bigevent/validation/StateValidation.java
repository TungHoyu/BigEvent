package com.albedo.bigevent.validation;

import com.albedo.bigevent.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext
     * @return false 不通过；true 通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null){
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿")){
            return true;
        }
        return false;
    }
}
