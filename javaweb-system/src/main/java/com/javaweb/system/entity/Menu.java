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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 目标
     */
    private String target;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 类型：1目录 2菜单 3节点
     */
    private Integer type;

    /**
     * 是否显示：1显示 2不显示
     */
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 子级菜单
     */
    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 是否选中
     */
    @TableField(exist = false)
    private boolean checked;

    /**
     * 是否打开
     */
    @TableField(exist = false)
    private boolean open;

    /**
     * 权限节点参数
     */
    @TableField(exist = false)
    private Integer[] checkedList;

}
