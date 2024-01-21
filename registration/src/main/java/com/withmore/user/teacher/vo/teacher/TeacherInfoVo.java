// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.vo.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 教师信息表表单Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
public class TeacherInfoVo {

    /**
     * 教师信息表ID
     */
    private Integer id;

    /**
     * 教师姓名
     */
    private String realName;

    /**
     * 教师工号
     */
    private String tchNumber;

    /**
     * 性别
     */
    private String gender;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 办公室位置
     */
    private String office;

    /**
     * 教师其他职责身份
     */
    private String identity;

    /**
     * 所属学院索引
     */
    private Integer collageId;

    /**
     * 所属组ID
     */
    private Integer groupId;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 微信表索引
     */
    private Integer wxId;

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

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}