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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标签
     */
    private String code;

    /**
     * 状态：1正常 2禁用
     */
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer sort;


}
