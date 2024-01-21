// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.user.wechat.entity.Route;
import com.withmore.user.wechat.query.RoleRouteQuery;
import com.withmore.user.wechat.query.RouteQuery;
import com.withmore.user.wechat.service.IRouteService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 微信小程序路由表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-28
 */
@RestController
@RequestMapping("/route")
public class RouteController extends BaseController {

    @Autowired
    private IRouteService routeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:route:index")
    @GetMapping("/index")
    public JsonResult index(RouteQuery query) {
        return routeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "微信小程序路由表", logType = LogType.INSERT)
    @RequiresPermissions("sys:route:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Route entity) {
        return routeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param routeId 记录ID
     * @return
     */
    @GetMapping("/info/{routeId}")
    public JsonResult info(@PathVariable("routeId") Integer routeId) {
        return routeService.info(routeId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "微信小程序路由表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:route:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Route entity) {
        return routeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param routeIds 记录ID
     * @return
     */
    @Log(title = "微信小程序路由表", logType = LogType.DELETE)
    @RequiresPermissions("sys:route:drop")
    @DeleteMapping("/delete/{routeIds}")
    public JsonResult delete(@PathVariable("routeIds") Integer[] routeIds) {
        return routeService.deleteByIds(routeIds);
    }

    /**
     * 小程序用户获取角色路由
     *
     * @param query 查询参数
     * @return
     */
    @GetMapping("/query/role")
    public JsonResultS query(RoleRouteQuery query) {
        return routeService.queryRoleRoute(query);
    }

}