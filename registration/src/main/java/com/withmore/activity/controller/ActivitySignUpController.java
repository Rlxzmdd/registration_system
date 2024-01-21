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
import com.withmore.activity.entity.ActivitySignUp;
import com.withmore.activity.query.ActivitySignUpQuery;
import com.withmore.activity.service.IActivitySignUpService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动报名信息表 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/activitysignup")
public class ActivitySignUpController extends BaseController {

    @Autowired
    private IActivitySignUpService activitySignUpService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:activitysignup:index")
    @GetMapping("/index")
    public JsonResult index(ActivitySignUpQuery query) {
        return activitySignUpService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动报名信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:activitysignup:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ActivitySignUp entity) {
        return activitySignUpService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param activitysignupId 记录ID
     * @return
     */
    @GetMapping("/info/{activitysignupId}")
    public JsonResult info(@PathVariable("activitysignupId") Integer activitysignupId) {
        return activitySignUpService.info(activitysignupId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动报名信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:activitysignup:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ActivitySignUp entity) {
        return activitySignUpService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param activitysignupIds 记录ID
     * @return
     */
    @Log(title = "活动报名信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:activitysignup:drop")
    @DeleteMapping("/delete/{activitysignupIds}")
    public JsonResult delete(@PathVariable("activitysignupIds") Integer[] activitysignupIds) {
        return activitySignUpService.deleteByIds(activitysignupIds);
    }

}