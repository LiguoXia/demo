package com.liguo.demo.core.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.liguo.demo.core.pojo.vo.ValidationResult;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/11/18 21:45
 * @since 0.0.1
 */
public class BeanValidateUtil {
    private BeanValidateUtil() {
    }

    /**
     * 验证器
     */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    /**
     * 校验实体，返回实体所有属性的校验结果
     *
     * @param obj bean对象
     * @param <T>
     * @return 所有属性的校验结果
     */
    public static <T> ValidationResult validateEntity(T obj) {
        //解析校验结果
        Set<ConstraintViolation<T>> validateSet = validator.validate(obj, Default.class);
        return buildValidationResult(validateSet);
    }

    /**
     * 校验实体，返回实体所有属性的校验结果
     *
     * @param obj bean对象
     * @param <T>
     * @return 所有属性的校验结果
     */
    public static <T> ValidationResult validateEntity(T obj, Class<?>... groups) {
        //解析校验结果
        Set<ConstraintViolation<T>> validateSet = validator.validate(obj, groups);
        return buildValidationResult(validateSet);
    }

    /**
     * 校验指定实体的指定属性是否存在异常
     *
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> validateSet = validator.validateProperty(obj, propertyName, Default.class);
        return buildValidationResult(validateSet);
    }

    /**
     * 将异常结果封装返回
     *
     * @param validateSet
     * @param <T>
     * @return
     */
    private static <T> ValidationResult buildValidationResult(Set<ConstraintViolation<T>> validateSet) {
        ValidationResult validationResult = new ValidationResult();
        if (CollectionUtils.isNotEmpty(validateSet)) {
            validationResult.setHasErrors(true);
            Map<String, String> errorMsgMap = new HashMap<>();
            for (ConstraintViolation<T> constraintViolation : validateSet) {
                errorMsgMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
            validationResult.setErrorMsg(errorMsgMap);
        }
        return validationResult;
    }

    /**
     * 带错误信息返回的校验
     * 格式： 错误信息
     *
     * @param t
     * @return
     */
    public static <T> String validateReturnMsg(T t, Class<?>... groups) {
        String errMsg = null;
        if (null == t) {
            throw new IllegalArgumentException("验证数据不能为空");
        }
        // 参数校验
        Set<ConstraintViolation<T>> violationSet;
        if (groups == null) {
            violationSet = validator.validate(t);
        } else {
            violationSet = validator.validate(t, groups);
        }
        if (CollectionUtils.isEmpty(violationSet)) {
            return null;
        }
        // 错误信息拼接
        errMsg = violationSet.stream()
                .map(item -> item.getMessage())
                .reduce((m1, m2) -> m1 + " " + m2)
                .orElse("参数输入有误！");
        return errMsg;
    }

    /**
     * 不带分组的手动校验
     *
     * @param t javaBean
     * @return 参数检验提示信息
     * @author liguo
     */
    public static <T> String validateReturnMsg(T t) {
        return validateReturnMsg(t, Default.class);
    }

    /**
     * 校验bean，有错抛出异常
     * <p>1.1版本：增加分组校验参数。by:liguo</p>
     *
     * @param t
     * @throws IllegalArgumentException 参数异常提示信息
     */
    public static <T> void validate(T t, Class<?>... groups) {
        String errMsg = validateReturnMsg(t, groups);
        if (StringUtils.isNotBlank(errMsg)) {
            throw new IllegalArgumentException(errMsg);
        }
    }

    /**
     * 不带分组的手动校验(抛异常接口)
     *
     * @param t javaBean
     * @throws IllegalArgumentException 参数异常提示信息
     */
    public static <T> void validate(T t) {
        validate(t, Default.class);
    }

}
