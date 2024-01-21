package com.withmore.event.todo.dto;

import com.javaweb.system.common.BaseQuery;
import com.withmore.event.todo.constant.FormAuditNoticeStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormAuditNoticeParamDto extends BaseQuery {
    /*每种类型表单显示条数*/
    private Integer subLimit;
    /*审核通知的状态*/
    @Setter(AccessLevel.NONE)
    private FormAuditNoticeStatus status;

    public void setStatus(String status) {
        if (status.equals(FormAuditNoticeStatus.FORM_AUDIT_WAIT.getStatus())) {
            this.status = FormAuditNoticeStatus.FORM_AUDIT_WAIT;
        } else if (status.equals(FormAuditNoticeStatus.FORM_AUDIT_ALL.getStatus())) {
            this.status = FormAuditNoticeStatus.FORM_AUDIT_ALL;
        } else if (status.equals(FormAuditNoticeStatus.FORM_AUDIT_EXCEPTION.getStatus())) {
            this.status = FormAuditNoticeStatus.FORM_AUDIT_EXCEPTION;
        } else if (status.equals(FormAuditNoticeStatus.FORM_AUDIT_OTHER.getStatus())) {
            this.status = FormAuditNoticeStatus.FORM_AUDIT_OTHER;
        } else {
            this.status = FormAuditNoticeStatus.FORM_AUDIT_ALL;
        }
    }
}
