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
import com.withmore.user.student.entity.StudentRemark;
import com.withmore.user.student.query.StudentRemarkQuery;
import com.withmore.user.student.service.IStudentRemarkService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生备注信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/studentremark")
public class StudentRemarkController extends BaseController {

    @Autowired
    private IStudentRemarkService studentRemarkService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:studentremark:index")
    @GetMapping("/index")
    public JsonResult index(StudentRemarkQuery query) {
        return studentRemarkService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生备注信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:studentremark:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody StudentRemark entity) {
        return studentRemarkService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param studentremarkId 记录ID
     * @return
     */
    @GetMapping("/info/{studentremarkId}")
    public JsonResult info(@PathVariable("studentremarkId") Integer studentremarkId) {
        return studentRemarkService.info(studentremarkId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学生备注信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:studentremark:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody StudentRemark entity) {
        return studentRemarkService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param studentremarkIds 记录ID
     * @return
     */
    @Log(title = "学生备注信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:studentremark:drop")
    @DeleteMapping("/delete/{studentremarkIds}")
    public JsonResult delete(@PathVariable("studentremarkIds") Integer[] studentremarkIds) {
        return studentRemarkService.deleteByIds(studentremarkIds);
    }

}