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

import com.javaweb.system.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_config_data")
public class ConfigData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 配置标题
     */
    private String title;

    /**
     * 配置编码
     */
    private String code;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置项
     */
    private String options;

    /**
     * 配置ID
     */
    private Integer configId;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 配置说明
     */
    private String note;


}
