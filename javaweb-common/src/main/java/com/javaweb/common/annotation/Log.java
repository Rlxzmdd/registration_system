// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.common.annotation;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.enums.OperType;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 日志类型
     */
    LogType logType() default LogType.OTHER;

    /**
     * 操作人类别
     */
    OperType operType() default OperType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
