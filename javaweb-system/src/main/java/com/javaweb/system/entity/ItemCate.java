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
import com.javaweb.system.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 栏目管理表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_item_cate")
public class ItemCate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 拼音(全)
     */
    private String pinyin;

    /**
     * 拼音(简)
     */
    private String code;

    /**
     * 封面
     */
    private String cover;

    /**
     * 状态：1启用 2停用
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

    /**
     * 是否含有子级
     */
    @TableField(exist = false)
    private boolean hasChildren;

}
