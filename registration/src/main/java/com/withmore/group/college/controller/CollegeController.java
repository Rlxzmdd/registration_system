// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.college.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.group.college.entity.College;
import com.withmore.group.college.query.CollegeQuery;
import com.withmore.group.college.service.ICollegeService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学院信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/college")
public class CollegeController extends BaseController {

    @Autowired
    private ICollegeService collegeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:college:index")
    @GetMapping("/index")
    public JsonResult index(CollegeQuery query) {
        return collegeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学院信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:college:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody College entity) {
        return collegeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param collegeId 记录ID
     * @return
     */
    @GetMapping("/info/{collegeId}")
    public JsonResult info(@PathVariable("collegeId") Integer collegeId) {
        return collegeService.info(collegeId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "学院信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:college:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody College entity) {
        return collegeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param collegeIds 记录ID
     * @return
     */
    @Log(title = "学院信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:college:drop")
    @DeleteMapping("/delete/{collegeIds}")
    public JsonResult delete(@PathVariable("collegeIds") Integer[] collegeIds) {
        return collegeService.deleteByIds(collegeIds);
    }

}