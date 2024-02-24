package com.withmore.event.form.vo.formItem;

import com.javaweb.system.common.BaseQuery;
import com.withmore.event.form.constant.FormAuditNoticeStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormItemStatusDto extends BaseQuery {
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
