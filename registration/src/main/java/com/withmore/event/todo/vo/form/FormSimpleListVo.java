package com.withmore.event.todo.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.withmore.event.todo.constant.FormAvailableStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class FormSimpleListVo implements FormAvailableStatus {
    /*表单ID*/
    private Integer id;
    /*表单标题*/
    private String title;
    /*开始填写时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /*结束填写时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /*表单Key*/
    private String formKey;
    /*我的提交状态*/
    private String submitStatus;
    /*表单状态*/
    private String formStatus;
    /*我的提交通过状态*/
    private Boolean passed;
    /*审核状态*/
    private Boolean auditStatus;
}
