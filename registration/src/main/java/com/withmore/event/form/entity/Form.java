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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import com.withmore.event.form.constant.FormAvailableStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 待办事项-填报事项
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_form")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class Form extends BaseEntity implements FormAvailableStatus {

    private static final long serialVersionUID = 1L;

    /**
     * 事项标题
     */
    private String title;

    /**
     * 发起人学号/工号
     */
    private String initiatorNumber;

    /**
     * 发起人类型
     */
    private String initiatorType;

    /**
     * 表单类型key
     */
    private String formKey;

    /**
     * 表单模版
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject template;

    /**
     * 开始填写时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 审核流程ID
     */
    private Integer reviewerId;

    /**
     * 是否需要审核
     */
    private Boolean audit;

    /**
     * 是否启用策略
     * 启用策略将忽略reviewerId
     */
    private Boolean enableStrategy;

    /**
     * 策略规则
     */
    private String strategy;
}