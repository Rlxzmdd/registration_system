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

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.activity.dto.UserIdentityDto;
import com.withmore.activity.entity.ActivityExamine;
import com.withmore.activity.query.ActivityExamineQuery;
import com.withmore.activity.service.IActivityExamineService;
import com.withmore.common.utils.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动核销表 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/activity_examine/")
public class ActivityExamineController extends BaseController {

    @Autowired
    private IActivityExamineService activityExamineService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    //@RequiresPermissions("sys:activityexamine:index")
    @GetMapping("/index")
    public JsonResult index(ActivityExamineQuery query) {
        return activityExamineService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "活动核销表", logType = LogType.INSERT)
    @RequiresPermissions("sys:activityexamine:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ActivityExamine entity) {
        return activityExamineService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param activityexamineId 记录ID
     */
    @GetMapping("/info/{activityexamineId}")
    public JsonResult info(@PathVariable("activityexamineId") Integer activityexamineId) {
        return activityExamineService.info(activityexamineId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "活动核销表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:activityexamine:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ActivityExamine entity) {
        return activityExamineService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param activityexamineIds 记录ID
     */
    @Log(title = "活动核销表", logType = LogType.DELETE)
    @RequiresPermissions("sys:activityexamine:drop")
    @DeleteMapping("/delete/{activityexamineIds}")
    public JsonResult delete(@PathVariable("activityexamineIds") Integer[] activityexamineIds) {
        return activityExamineService.deleteByIds(activityexamineIds);
    }

    /**
     * 身份信息核销
     * 设备1 接口
     * 对应活动ID=18
     *
     * @param param 请求参数
     */
    @PostMapping("/identity/DoorController/visitorRegistrationRecordTraffic")
    public JsonResultS identity(@RequestBody UserIdentityDto param) {
        return activityExamineService.identity(param, 18);
    }

    /**
     * 身份信息核销
     * 设备2 接口
     * 对应活动ID=19
     *
     * @param param 请求参数
     */
    @PostMapping("/identity2/DoorController/visitorRegistrationRecordTraffic")
    public JsonResultS identity2(@RequestBody UserIdentityDto param) {
        return activityExamineService.identity(param, 19);
    }

}