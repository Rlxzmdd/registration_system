// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 审核人映射表
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_form_reviewer")
public class FormReviewer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 第一审核人
     */
    private String firstNumber;

    /**
     * 第一用户类型
     */
    private String firstType;

    /**
     * 第二审核人
     */
    private String secondNumber;

    /**
     * 第二审核人类型
     */
    private String secondType;

    /**
     * 第三审核人
     */
    private String thirdNumber;

    /**
     * 第三审核人类型
     */
    private String thirdType;

    /**
     * 第四审核人
     */
    private String fourthNumber;

    /**
     * 第四审核人类型
     */
    private String fourthType;

}