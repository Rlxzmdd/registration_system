// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.vo.route;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 微信小程序路由表列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-08-28
 */
@Data
public class RouteListVo {

    /**
    * 微信小程序路由表ID
    */
    private Integer id;

    /**
     * 角色Key
     */
    private String role;

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
     * 记录有效性
     */
    private Integer mark;

}