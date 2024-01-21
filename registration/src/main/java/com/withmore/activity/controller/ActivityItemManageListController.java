// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.activity.entity.ActivityItemManageList;
import com.withmore.activity.query.ActivityItemManageListQuery;
import com.withmore.activity.service.IActivityItemManageListService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动管理员信息表 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/activityitemmanagelist")
public class ActivityItemManageListController extends BaseController {

    @Autowired
    private IActivityItemManageListService activityItemManageListService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:activityitemmanagelist:index")
    @GetMapping("/index")
    public JsonResult index(ActivityItemManageListQuery query) {
        return activityItemManageListService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动管理员信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:activityitemmanagelist:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ActivityItemManageList entity) {
        return activityItemManageListService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param activityitemmanagelistId 记录ID
     * @return
     */
    @GetMapping("/info/{activityitemmanagelistId}")
    public JsonResult info(@PathVariable("activityitemmanagelistId") Integer activityitemmanagelistId) {
        return activityItemManageListService.info(activityitemmanagelistId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动管理员信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:activityitemmanagelist:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ActivityItemManageList entity) {
        return activityItemManageListService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param activityitemmanagelistIds 记录ID
     * @return
     */
    @Log(title = "活动管理员信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:activityitemmanagelist:drop")
    @DeleteMapping("/delete/{activityitemmanagelistIds}")
    public JsonResult delete(@PathVariable("activityitemmanagelistIds") Integer[] activityitemmanagelistIds) {
        return activityItemManageListService.deleteByIds(activityitemmanagelistIds);
    }

}