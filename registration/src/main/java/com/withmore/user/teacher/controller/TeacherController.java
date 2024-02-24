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

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.withmore.user.teacher.entity.Teacher;
import com.withmore.user.teacher.query.TeacherQuery;
import com.withmore.user.teacher.service.ITeacherService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 教师信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private ITeacherService teacherService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:teacher:index")
    @GetMapping("/index")
    public JsonResult index(TeacherQuery query) {
        return teacherService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "教师信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:teacher:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Teacher entity) {
        return teacherService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param teacherId 记录ID
     */
    @GetMapping("/info/{teacherId}")
    public JsonResult info(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.info(teacherId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "教师信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:teacher:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Teacher entity) {
        return teacherService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param teacherIds 记录ID
     */
    @Log(title = "教师信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:teacher:drop")
    @DeleteMapping("/delete/{teacherIds}")
    public JsonResult delete(@PathVariable("teacherIds") Integer[] teacherIds) {
        return teacherService.deleteByIds(teacherIds);
    }

}