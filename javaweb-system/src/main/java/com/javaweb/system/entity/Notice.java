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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_notice")
public class Notice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 来源：1内部通知 2外部新闻
     */
    private Integer source;

    /**
     * 是否置顶：1是 2否
     */
    private Integer isTop;

    /**
     * 阅读量
     */
    private Integer browse;

    /**
     * 状态：1已发布 2待发布
     */
    private Integer status;


}
