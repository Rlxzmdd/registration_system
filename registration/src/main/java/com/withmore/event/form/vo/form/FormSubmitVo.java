package com.withmore.event.form.vo.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormSubmitVo {
    /*提交的表单UUID*/
    private String formUUID;
    /*当前审核结果*/
    private Boolean auditPassed;
}
