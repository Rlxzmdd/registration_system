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
import com.withmore.user.student.entity.StudentLabel;
import com.withmore.user.student.query.StudentLabelQuery;
import com.withmore.user.student.service.IStudentLabelService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生标签信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/studentlabel")
public class StudentLabelController extends BaseController {

    @Autowired
    private IStudentLabelService studentLabelService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:studentlabel:index")
    @GetMapping("/index")
    public JsonResult index(StudentLabelQuery query) {
        return studentLabelService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生标签信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:studentlabel:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody StudentLabel entity) {
        return studentLabelService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param studentlabelId 记录ID
     * @return
     */
    @GetMapping("/info/{studentlabelId}")
    public JsonResult info(@PathVariable("studentlabelId") Integer studentlabelId) {
        return studentLabelService.info(studentlabelId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生标签信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:studentlabel:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody StudentLabel entity) {
        return studentLabelService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param studentlabelIds 记录ID
     * @return
     */
    @Log(title = "学生标签信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:studentlabel:drop")
    @DeleteMapping("/delete/{studentlabelIds}")
    public JsonResult delete(@PathVariable("studentlabelIds") Integer[] studentlabelIds) {
        return studentLabelService.deleteByIds(studentlabelIds);
    }

}