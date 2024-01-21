// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.entity;

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
 * 教师信息表
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_teacher")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 登录密码
     */
    private String password;

    /**
     * 教师其他职责身份
     */
    private String identity;

    /**
     * 所属学院索引
     */
    private Integer collegeId;

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

}