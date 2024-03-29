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
 * 审核通知表
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_form_audit_notice")
public class FormAuditNotice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表单类型key
     */
    private String formKey;

    /**
     * 表单item UUID
     */
    private String itemUuid;

    /**
     * 审核记录
     */
    private Integer auditId;

    /**
     * 审核人工号、学号
     */
    private String reviewerNumber;

    /**
     * 审核人类型
     */
    private String reviewerType;

}