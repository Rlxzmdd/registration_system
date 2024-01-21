// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.permission.vo.permissionnode;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 权限节点表-待办事项可视权限列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-27
 */
@Data
public class PermissionNodeListVo {

    /**
    * 权限节点表-待办事项可视权限ID
    */
    private Integer id;

    /**
     * 一级权限
     */
    private String level1;

    /**
     * 二级权限
     */
    private String level2;

    /**
     * 三级权限
     */
    private String level3;

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
     * 记录有效期性
     */
    private Integer mark;

}