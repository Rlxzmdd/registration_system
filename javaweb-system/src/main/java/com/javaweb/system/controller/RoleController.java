// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Role;
import com.javaweb.system.query.RoleQuery;
import com.javaweb.system.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 获取角色列表
     *
     * @param roleQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:role:index")
    @GetMapping("/index")
    public JsonResult index(RoleQuery roleQuery) {
        return roleService.getList(roleQuery);
    }

    /**
     * 添加角色
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "角色管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:role:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 编辑角色
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "角色管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:role:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 删除角色
     *
     * @param roleIds 角色ID
     * @return
     */
    @Log(title = "角色管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:role:delete")
    @DeleteMapping("/delete/{roleIds}")
    public JsonResult delete(@PathVariable("roleIds") Integer[] roleIds) {
        return roleService.deleteByIds(roleIds);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/getRoleList")
    public JsonResult getRoleList() {
        return roleService.getRoleList();
    }

    /**
     * 获取角色菜单列表
     *
     * @param roleId 角色ID
     * @return
     */
    @GetMapping("/getMenuList/{roleId}")
    public JsonResult getMenuList(@PathVariable("roleId") Integer roleId) {
        return roleService.getMenuList(roleId);
    }

    /**
     * 保存角色权限数据
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return
     */
    @RequiresPermissions("sys:role:permission")
    @PutMapping("/savePermission/{roleId}")
    public JsonResult savePermission(@PathVariable("roleId") Integer roleId, @RequestBody String[] menuIds) {
        return roleService.savePermission(roleId, menuIds);
    }

}
