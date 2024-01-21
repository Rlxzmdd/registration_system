package com.withmore.event.todo.vo.formItem;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class FormDataItemVo {
    /*表单内容*/
    private JSONObject content;
    /*表单UUID*/
    private String uuid;
    /*表单模版KEY*/
    private String formKey;
    /*是否审核完成*/
    private Boolean auditFinish;
    /*是否审核通过*/
    private Boolean passed;
    /*最后审核人或当前谁在审核*/
    private String auditName;
}
