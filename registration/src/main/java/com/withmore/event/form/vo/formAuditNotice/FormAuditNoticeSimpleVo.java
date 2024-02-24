package com.withmore.event.form.vo.formAuditNotice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FormAuditNoticeSimpleVo {
    /*需要审核的表单模版key*/
    private String formKey;
    /*提交记录UUID*/
    private String itemUuid;
    /*表单模版标题*/
    private String title;
    /*提交人姓名*/
    private String submitName;
    /*提交人学号*/
    private String submitNumber;
    /*提交人类型*/
    private String userType;
    /*提交人班级*/
    private String classes;
    /*是否审核*/
    private Boolean auditStatus;
    /*是否审核通过*/
    private Boolean passed;
    /*审核信息当前所处状态*/
    private String status;
    /*提交审核时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;
}
