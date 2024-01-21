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
import com.javaweb.system.entity.Layout;
import com.javaweb.system.query.LayoutQuery;
import com.javaweb.system.service.ILayoutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 页面布局管理 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/layout")
public class LayoutController extends BaseController {

    @Autowired
    private ILayoutService layoutService;

    /**
     * 获取布局列表
     *
     * @param layoutQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:layout:index")
    @GetMapping("/index")
    public JsonResult index(LayoutQuery layoutQuery) {
        return layoutService.getList(layoutQuery);
    }

    /**
     * 获取布局详情
     *
     * @param layoutId 布局ID
     * @return
     */
    @GetMapping("/info/{layoutId}")
    public JsonResult info(@PathVariable("layoutId") Integer layoutId) {
        return layoutService.info(layoutId);
    }

    /**
     * 添加布局
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:layout:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Layout entity) {
        return layoutService.edit(entity);
    }

    /**
     * 编辑布局
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:layout:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Layout entity) {
        return layoutService.edit(entity);
    }

    /**
     * 删除布局
     *
     * @param layoutIds 布局ID
     * @return
     */
    @Log(title = "布局管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:layout:delete")
    @DeleteMapping("/delete/{layoutIds}")
    public JsonResult delete(@PathVariable("layoutIds") Integer[] layoutIds) {
        return layoutService.deleteByIds(layoutIds);
    }

}
