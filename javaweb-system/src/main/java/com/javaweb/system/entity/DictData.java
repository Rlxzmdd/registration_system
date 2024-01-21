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
 * 字典项管理表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_data")
public class DictData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 字典项值
     */
    private String code;

    /**
     * 字典类型ID
     */
    private Integer dictId;

    /**
     * 状态：1在用 2停用
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


}
