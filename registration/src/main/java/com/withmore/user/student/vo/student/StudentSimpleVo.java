package com.withmore.user.student.vo.student;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentSimpleVo {
    /*所属学院*/
    private String college;
    /*所属专业*/
    private String major;
    /*所属班级*/
    private String classes;
    /*所属班级ID*/
    private Integer classesId;
    /*姓名*/
    private String name;
    /*学号*/
    private String number;
}
