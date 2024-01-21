package com.withmore.event.todo.constant;


/**
 * 表单审核通知状态
 */
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


    String status;

    FormAuditNoticeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
