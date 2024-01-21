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
import com.withmore.activity.entity.ActivityItemProfile;
import com.withmore.activity.query.ActivityItemProfileQuery;
import com.withmore.activity.service.IActivityItemProfileService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动正文表 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/activityitemprofile")
public class ActivityItemProfileController extends BaseController {

    @Autowired
    private IActivityItemProfileService activityItemProfileService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:activityitemprofile:index")
    @GetMapping("/index")
    public JsonResult index(ActivityItemProfileQuery query) {
        return activityItemProfileService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动正文表", logType = LogType.INSERT)
    @RequiresPermissions("sys:activityitemprofile:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ActivityItemProfile entity) {
        return activityItemProfileService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param activityitemprofileId 记录ID
     * @return
     */
    @GetMapping("/info/{activityitemprofileId}")
    public JsonResult info(@PathVariable("activityitemprofileId") Integer activityitemprofileId) {
        return activityItemProfileService.info(activityitemprofileId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动正文表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:activityitemprofile:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ActivityItemProfile entity) {
        return activityItemProfileService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param activityitemprofileIds 记录ID
     * @return
     */
    @Log(title = "活动正文表", logType = LogType.DELETE)
    @RequiresPermissions("sys:activityitemprofile:drop")
    @DeleteMapping("/delete/{activityitemprofileIds}")
    public JsonResult delete(@PathVariable("activityitemprofileIds") Integer[] activityitemprofileIds) {
        return activityItemProfileService.deleteByIds(activityitemprofileIds);
    }

}