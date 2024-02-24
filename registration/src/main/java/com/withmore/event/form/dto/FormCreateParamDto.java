package com.withmore.event.form.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * 创建表单模版DTO
 */
@Data
@Accessors(chain = true)
public class FormCreateParamDto {
    /*模版*/
    private JSONObject template;
    /*表单标题*/
    private String title;
    /*是否需要审核*/
    private Boolean audit;
    /*可视权限*/
    private List<Integer> permissions;
    /*审核流ID*/
    private Integer reviewerId;
    /*开始填写时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /*结束填写时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
