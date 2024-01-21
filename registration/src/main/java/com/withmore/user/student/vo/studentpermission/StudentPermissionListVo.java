// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.vo.studentpermission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 学生-学生可访问权限表列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-29
 */
@Data
public class StudentPermissionListVo {

    /**
    * 学生-学生可访问权限表ID
    */
    private Integer id;

    /**
     * 学生学号
     */
    private String stuNumber;

    /**
     * 权限节点索引
     */
    private Integer permissionId;

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
     * 创建用户
     */
    private Integer createUser;

    /**
     * 更新用户
     */
    private Integer updateUser;

    /**
     * 记录有效性
     */
    private Integer mark;

}