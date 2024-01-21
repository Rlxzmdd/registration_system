// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.vo.studentremark;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 学生备注信息表表单Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
public class StudentRemarkInfoVo {

    /**
     * 学生备注信息表ID
     */
    private Integer id;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 教师工号
     */
    private String tchNumber;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 备注内容
     */
    private String remark;

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