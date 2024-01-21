// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员角色表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;


}
