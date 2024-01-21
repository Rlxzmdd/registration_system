package com.withmore.activity.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityClassExamineExcelVo {
    @Excel(name = "活动标题")
    private String title;

    @Excel(name = "学院")
    private String collegeName;

    @Excel(name = "班级")
    private String className;

    @Excel(name = "学生名字")
    private String stuName;

    @Excel(name = "学生学号")
    private String stuNumber;

    @Excel(name = "是否被核销")
    private Integer isExamine;

    @Excel(name = "顺序")
    private Integer serialNum;

    @Excel(name = "核销人")
    private String examinerName;

    //todo 添加是否报名、通过
//    @Excel(name = "是否报名")
//    private Integer isSignup;
//
//    @Excel(name = "是否报名通过")
//    private Integer isApply;

    @Excel(name = "核销时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examineTime;
}
