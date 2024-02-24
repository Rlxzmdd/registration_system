package com.withmore.event.form.dto;

import lombok.Data;

/**
 * 用户表单提交状态DTO
 */
@Data
public class FormSubmitStatusDto {
    /*表单formKey*/
    private String formKey;
    /*提交状态*/
    private String status;
    /*是否通过*/
    private Boolean passed;
    /*审核状态*/
    private Boolean auditStatus;
}
