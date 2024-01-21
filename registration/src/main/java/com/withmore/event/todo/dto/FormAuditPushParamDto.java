package com.withmore.event.todo.dto;

import lombok.Data;

@Data
public class FormAuditPushParamDto {
    /*表单数据UUID*/
    private String uuid;
    /*是否通过*/
    private Boolean through;
    /*意见*/
    private String opinion;
    /*表单类型唯一Key*/
    private String formKey;
}
