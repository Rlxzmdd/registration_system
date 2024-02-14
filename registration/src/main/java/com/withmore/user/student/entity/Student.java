// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 学生信息表
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_student")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学生姓名
     */
    private String realName;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别
     */
    private String gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 政治面貌
     */
    private String politic;

    /**
     * 通知书编号
     */
    private String serialNumber;

    /**
     * 考生号
     */
    private String examNumber;

    /**
     * 学生状态
     */
    private String stuStatus;

    /**
     * 所属学院索引
     */
    private Integer collegeId;

    /**
     * 所属班级索引
     */
    private Integer classesId;

    /**
     * 宿舍信息索引
     */
    private Integer dormId;

    /**
     * 所属专业索引
     */
    private Integer majorId;

    /**
     * 微信表索引
     */
    private Integer wxId;

    /**
     * 学生头像UUID
     */
    private String avatar;

}