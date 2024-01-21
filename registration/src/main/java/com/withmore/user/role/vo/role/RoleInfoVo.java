// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户权限表表单Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
@Data
public class RoleInfoVo {

    /**
     * 用户权限表ID
     */
    private Integer id;

    /**
     * 用户学号/工号
     */
    private String userNumber;

    /**
     * 角色ID
     */
    private Integer roleId;

}