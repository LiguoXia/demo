package com.liguo.demo.core.config;

import com.liguo.demo.core.enums.ResultCodeEnum;
import com.liguo.demo.core.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/15 23:51
 * @since 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Result bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！错误码：{},原因是：{}", e.getErrorCode(), e.getErrorMsg());
        return Result.success().setCode(e.getErrorMsg()).setMessage(e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return Result.failure(ResultCodeEnum.SERVER_ERROR);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return Result.failure(ResultCodeEnum.SERVER_ERROR);
    }
}
