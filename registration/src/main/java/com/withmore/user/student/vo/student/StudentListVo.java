// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.vo.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 学生信息表列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
public class StudentListVo {

    /**
    * 学生信息表ID
    */
    private Integer id;

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
     * 学生状态
     */
    private String stuStatus;

    /**
     * 所属学院索引
     */
    private Integer collageId;

    /**
     * 所属专业索引
     */
    private Integer majorId;

    /**
     * 微信表索引
     */
    private Integer wxId;

    /**
     * 记录创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 记录更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建记录用户
     */
    private Integer createUser;

    /**
     * 更新记录用户
     */
    private Integer updateUser;

    /**
     * 记录有效性
     */
    private Integer mark;

}