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
import com.javaweb.system.common.BaseController;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.query.MenuQuery;
import com.javaweb.system.service.IMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单列表
     *
     * @param menuQuery 查询条件
     */
    @RequiresPermissions("sys:menu:index")
    @GetMapping("/index")
    public JsonResult index(MenuQuery menuQuery) {
        return menuService.getList(menuQuery);
    }

    /**
     * 获取菜单详情
     *
     * @param menuId 菜单ID
     */
    @GetMapping("/info/{menuId}")
    public JsonResult info(@PathVariable("menuId") Integer menuId) {
        return menuService.info(menuId);
    }

    /**
     * 添加菜单
     *
     * @param entity 实体对象
     */
    @Log(title = "菜单管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:menu:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Menu entity) {
        return menuService.edit(entity);
    }

    /**
     * 编辑菜单
     *
     * @param entity 实体对象
     */
    @Log(title = "菜单管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:menu:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Menu entity) {
        return menuService.edit(entity);
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     */
    @Log(title = "菜单管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:menu:delete")
    @DeleteMapping("/delete/{menuId}")
    public JsonResult delete(@PathVariable("menuId") Integer menuId) {
        return menuService.deleteById(menuId);
    }

    /**
     * 获取所有菜单列表
     *
     */
    @GetMapping("/getMenuAll")
    public JsonResult getMenuAll() {
        List<Menu> menuList = menuService.getMenuAll();
        return JsonResult.success(menuList);
    }

}
