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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 待办事项-用户提交的表单
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "event_form_item", autoResultMap = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class FormItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表单内容
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject content;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 表单类型key
     */
    private String formKey;

    /**
     * 提交人学号
     */
    private String submitNumber;

    /**
     * 表单唯一UUID
     */
    private String itemUuid;

    /**
     * 审核到了第几审核人
     */
    private Integer auditStep = 1;

    /**
     * 审核是否完成
     */
    private Boolean auditFinish;

    /**
     * 审核是否通过
     */
    private Boolean auditPassed;

}