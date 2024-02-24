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
import com.javaweb.system.entity.Dept;
import com.javaweb.system.query.DeptQuery;
import com.javaweb.system.service.IDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    /**
     * 获取部门列表
     *
     * @param deptQuery 查询条件
     */
    @RequiresPermissions("sys:dept:index")
    @GetMapping("/index")
    public JsonResult index(DeptQuery deptQuery) {
        return deptService.getList(deptQuery);
    }

    /**
     * 添加部门
     *
     * @param entity 实体对象
     */
    @Log(title = "部门管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:dept:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dept entity) {
        return deptService.edit(entity);
    }

    /**
     * 编辑部门
     *
     * @param entity 实体对象
     */
    @Log(title = "部门管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:dept:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Dept entity) {
        return deptService.edit(entity);
    }

    /**
     * 删除部门
     *
     * @param deptId 部门ID
     */
    @Log(title = "部门管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:dept:delete")
    @DeleteMapping("/delete/{deptId}")
    public JsonResult delete(@PathVariable("deptId") Integer deptId) {
        return deptService.deleteById(deptId);
    }

    /**
     * 获取部门列表
     *
     */
    @GetMapping("/getDeptList")
    public JsonResult getDeptList() {
        return deptService.getDeptList();
    }

}
