// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.entity;

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
 * 表单审核策略表
 * </p>
 *
 * @author Cheney
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_reviewer_strategy")
public class ReviewerStrategy extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表单Key
     */
    private String formKey;

    /**
     * 策略规则
     */
    private String strategy;

    /**
     * 规则对应的审核流id
     */
    private Integer reviewerId;

    /**
     * 策略组值
     */
    private String strategyValue;

}