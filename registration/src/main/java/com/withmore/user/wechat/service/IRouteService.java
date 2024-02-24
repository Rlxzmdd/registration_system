// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.IBaseService;
import com.withmore.user.wechat.entity.Route;
import com.withmore.user.wechat.query.RoleRouteQuery;

/**
 * <p>
 * 微信小程序路由表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-08-28
 */
public interface IRouteService extends IBaseService<Route> {

    /**
     * 查询角色路由
     */
    JsonResultS queryRoleRoute(RoleRouteQuery query);
}