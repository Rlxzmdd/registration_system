package com.withmore.common;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller统一错误处理器
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 认证异常
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public JsonResultS exceptionHandler(AuthenticationException e, HttpServletResponse response) {
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0220);
    }

    /**
     * 授权异常
     * 通常为无接口访问权限时由shior 抛出
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public JsonResultS exceptionHandler(AuthorizationException e, HttpServletResponse response) {
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0312);
    }

    /**
     * 参数为null 抛出
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public JsonResultS exceptionHandler(NullPointerException e, HttpServletResponse response) {
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0410);
    }

    /**
     * 通用异常处理
     * 统一返回500
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResultS exceptionHandler(Exception e, HttpServletResponse response) {
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0500);
    }
}
