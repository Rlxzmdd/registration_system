package com.withmore.event.todo.vo.formItem;

import lombok.Data;

@Data
public class FormDataItemStatusVO {
    /*表单UUID*/
    private String uuid;
    /*表单模版KEY*/
    private String formKey;
    /*是否审核完成*/
    private Boolean auditFinish;
    /*是否审核通过*/
    private Boolean passed;
    /*当前审核人是否审核了此项*/
    private Boolean isAudit;
    /*最后审核人或当前谁在审核*/
    private String auditName;
    /*表单标题*/
    private String title;
    /*审核意见*/
    private String opinion;
}
