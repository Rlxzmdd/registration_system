// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.user.student.entity.StudentPermission;
import com.withmore.user.student.query.StudentPermissionQuery;
import com.withmore.user.student.service.IStudentPermissionService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生-学生可访问权限表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/student/permission")
public class StudentPermissionController extends BaseController {

    @Autowired
    private IStudentPermissionService studentPermissionService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:student_permission:index")
    @GetMapping("/index")
    public JsonResult index(StudentPermissionQuery query) {
        return studentPermissionService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生-学生可访问权限表", logType = LogType.INSERT)
    @RequiresPermissions("sys:student_permission:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody StudentPermission entity) {
        return studentPermissionService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param studentpermissionId 记录ID
     * @return
     */
    @GetMapping("/info/{id}")
    public JsonResult info(@PathVariable("id") Integer studentpermissionId) {
        return studentPermissionService.info(studentpermissionId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生-学生可访问权限表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:student_permission:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody StudentPermission entity) {
        return studentPermissionService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param studentpermissionIds 记录ID
     * @return
     */
    @Log(title = "学生-学生可访问权限表", logType = LogType.DELETE)
    @RequiresPermissions("sys:student_permission:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] studentpermissionIds) {
        return studentPermissionService.deleteByIds(studentpermissionIds);
    }

}