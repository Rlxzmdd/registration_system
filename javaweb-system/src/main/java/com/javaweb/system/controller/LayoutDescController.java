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
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.query.LayoutDescQuery;
import com.javaweb.system.service.ILayoutDescService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 布局描述管理 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/layoutdesc")
public class LayoutDescController extends BaseController {

    @Autowired
    private ILayoutDescService layoutDescService;

    /**
     * 获取布局描述列表
     *
     * @param layoutDescQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:layoutdesc:index")
    @GetMapping("/index")
    public JsonResult index(LayoutDescQuery layoutDescQuery) {
        return layoutDescService.getList(layoutDescQuery);
    }

    /**
     * 添加布局描述
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局描述", logType = LogType.INSERT)
    @RequiresPermissions("sys:layoutdesc:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody LayoutDesc entity) {
        return layoutDescService.edit(entity);
    }

    /**
     * 编辑布局描述
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局描述", logType = LogType.UPDATE)
    @RequiresPermissions("sys:layoutdesc:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody LayoutDesc entity) {
        return layoutDescService.edit(entity);
    }

    /**
     * 删除布局描述
     *
     * @param layoutDescIds 布局描述ID
     * @return
     */
    @Log(title = "布局描述", logType = LogType.DELETE)
    @RequiresPermissions("sys:layoutdesc:delete")
    @DeleteMapping("/delete/{layoutDescIds}")
    public JsonResult delete(@PathVariable("layoutDescIds") Integer[] layoutDescIds) {
        return layoutDescService.deleteByIds(layoutDescIds);
    }

}
