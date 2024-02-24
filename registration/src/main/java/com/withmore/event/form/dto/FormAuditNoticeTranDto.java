package com.withmore.event.form.dto;

import com.withmore.event.form.entity.FormAuditNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormAuditNoticeTranDto extends FormAuditNotice {

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 是否审核通过
     */
    private Boolean isThrough;
}
