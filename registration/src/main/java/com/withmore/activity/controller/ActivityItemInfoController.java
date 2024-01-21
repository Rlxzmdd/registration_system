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
import com.withmore.activity.entity.ActivityItemInfo;
import com.withmore.activity.query.ActivityItemInfoQuery;
import com.withmore.activity.service.IActivityItemInfoService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动信息表 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/activityiteminfo")
public class ActivityItemInfoController extends BaseController {

    @Autowired
    private IActivityItemInfoService activityItemInfoService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:activityiteminfo:index")
    @GetMapping("/index")
    public JsonResult index(ActivityItemInfoQuery query) {
        return activityItemInfoService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:activityiteminfo:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ActivityItemInfo entity) {
        return activityItemInfoService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param activityiteminfoId 记录ID
     * @return
     */
    @GetMapping("/info/{activityiteminfoId}")
    public JsonResult info(@PathVariable("activityiteminfoId") Integer activityiteminfoId) {
        return activityItemInfoService.info(activityiteminfoId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "活动信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:activityiteminfo:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ActivityItemInfo entity) {
        return activityItemInfoService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param activityiteminfoIds 记录ID
     * @return
     */
    @Log(title = "活动信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:activityiteminfo:drop")
    @DeleteMapping("/delete/{activityiteminfoIds}")
    public JsonResult delete(@PathVariable("activityiteminfoIds") Integer[] activityiteminfoIds) {
        return activityItemInfoService.deleteByIds(activityiteminfoIds);
    }

}