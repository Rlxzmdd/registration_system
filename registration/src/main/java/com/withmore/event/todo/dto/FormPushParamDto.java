package com.withmore.event.todo.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 表单推送DTO
 */
@Data
public class FormPushParamDto {
    /*表单数据*/
    private JSONObject data;
    /*是否允许覆盖*/
    private Boolean override;
}
