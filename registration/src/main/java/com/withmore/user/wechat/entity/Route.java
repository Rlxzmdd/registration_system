// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 微信小程序路由表
 * </p>
 *
 * @author Cheney
 * @since 2021-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wechat_route")
public class Route extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色Key
     */
    private Integer roleId;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 图标名
     */
    private String icon;

    /**
     * 页面类型
     */
    private String type;

    /**
     * 路由地址
     */
    private String urlName;

}