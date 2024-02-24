package com.withmore.event.form.constant;


import lombok.Getter;

/**
 * 表单审核通知状态
 */
@Getter
public enum FormAuditNoticeStatus {
    /*等待审核的表单通知*/
    FORM_AUDIT_WAIT("wait"),
    /*已完成并拒绝的表单通知*/
    FORM_AUDIT_EXCEPTION("exception"),
    /*审核通过*/
    FORM_AUDIT_PASSED("passed"),
    /*审核拒绝*/
    FORM_AUDIT_REJECT("reject"),
    /*默认情况*/
    FORM_AUDIT_ALL("all"),
    /*已完成并通过的表单通知*/
    FORM_AUDIT_OTHER("other");


    final String status;

    FormAuditNoticeStatus(String status) {
        this.status = status;
    }

}
