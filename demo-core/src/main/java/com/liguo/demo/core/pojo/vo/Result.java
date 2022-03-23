package com.liguo.demo.core.pojo.vo;

import com.liguo.demo.core.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * RestFul 统一返回结果
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/18
 * @since 0.0.1
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final transient String DEFAULT_SUCCESS_CODE = "0";
    public static final transient String DEFAULT_ERROR_CODE = "-1";
    @ApiModelProperty("是否响应成功")
    private Boolean success;
    @ApiModelProperty("0:成功;其他:失败错误码")
    private String code;
    @ApiModelProperty("返回的结果")
    private T data;
    @ApiModelProperty("错误信息，给开发者使用。（可选）")
    private String message;
    @ApiModelProperty("提示信息，终端用户使用。（可选）")
    private String info;
    @ApiModelProperty("请求id")
    private String requestId;

    /**
     * 正常返回
     *
     * @param data
     */
    public Result(T data) {
        this.data = data;
        this.code = "0";
        this.message = "success";
    }

    /**
     * 枚举返回
     *
     * @param resultCode
     */
    private Result(ResultCodeEnum resultCode) {
        this.success = false;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 错误返回
     */
    public Result() {
        this.code = "-1";
        this.message = "failure";
    }

    public boolean isSuccess() {
        return "0".equals(this.code);
    }

    public static Result success() {
        return new Result((Object) null);
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result DefaultFailure(String msg) {
        Result result = new Result();
        result.setCode("-1");
        result.setMessage(msg);
        return result;
    }

    /**
     * 通用返回失败
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(ResultCodeEnum resultCode) {
        return new Result<T>(resultCode);
    }

    public String getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getInfo() {
        return this.info;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Result<T> setCode(final String code) {
        this.code = code;
        return this;
    }

    public Result<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public Result<T> setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Result<T> setInfo(final String info) {
        this.info = info;
        return this;
    }

    public Result<T> setRequestId(final String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String toString() {
        return "Result(code=" + this.getCode() + ", data=" + this.getData() + ", message=" + this.getMessage() + ", info=" + this.getInfo() + ", requestId=" + this.getRequestId() + ")";
    }
}