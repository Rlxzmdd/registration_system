// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.user.teacher.entity.TeacherPermission;
import com.withmore.user.teacher.query.TeacherPermissionQuery;
import com.withmore.user.teacher.service.ITeacherPermissionService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 教师可访问权限表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-01
 */
@RestController
@RequestMapping("/teacher_permission")
public class TeacherPermissionController extends BaseController {

    @Autowired
    private ITeacherPermissionService teacherPermissionService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:teacher_permission:index")
    @GetMapping("/index")
    public JsonResult index(TeacherPermissionQuery query) {
        return teacherPermissionService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "教师可访问权限表", logType = LogType.INSERT)
    @RequiresPermissions("sys:teacher_permission:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody TeacherPermission entity) {
        return teacherPermissionService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return teacherPermissionService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "教师可访问权限表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:teacher_permission:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody TeacherPermission entity) {
        return teacherPermissionService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "教师可访问权限表", logType = LogType.DELETE)
    @RequiresPermissions("sys:teacher_permission:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return teacherPermissionService.deleteByIds(Ids);
    }

}