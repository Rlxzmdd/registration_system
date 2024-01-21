package com.withmore.activity.vo.activity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityExamineVo {

    /**
     * 活动核销表ID
     */
    private Integer id;

    private Integer activityItemId;

    @Excel(name = "顺序")
    private Integer serialNum;

    @Excel(name = "活动标题")
    private String title;

    @Excel(name = "用户工号")
    private String examinedNumber;

    @Excel(name = "用户身份", readConverterExp = "student=学生,teacher=教师")
    private String examinedType;

    @Excel(name = "用户名称")
    private String examinedName;

    @Excel(name = "用户所在学院")
    private String examinedCollege;
    @Excel(name = "用户所在班级")
    private String examinedClasses;
    @Excel(name = "用户所在部门")
    private String examinedDepartment;

    @Excel(name = "核销人工号")
    private String examinerNumber;

    @Excel(name = "核销人身份", readConverterExp = "student=学生,teacher=教师")
    private String examinerType;

    @Excel(name = "核销人名称")
    private String examinerName;

    @Excel(name = "核销人所在学院")
    private String examinerCollege;
    @Excel(name = "核销人所在班级")
    private String examinerClasses;
    @Excel(name = "核销人所在部门")
    private String examinerDepartment;


    /**
     *
     */
    @Excel(name = "核销时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examineTime;


}
