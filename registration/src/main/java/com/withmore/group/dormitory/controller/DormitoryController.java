// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.dormitory.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.withmore.group.dormitory.entity.Dormitory;
import com.withmore.group.dormitory.query.DormitoryQuery;
import com.withmore.group.dormitory.service.IDormitoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 宿舍信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-09-11
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController extends BaseController {

    @Autowired
    private IDormitoryService dormitoryService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:dormitory:index")
    @GetMapping("/index")
    public JsonResult index(DormitoryQuery query) {
        return dormitoryService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "宿舍信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:dormitory:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dormitory entity) {
        return dormitoryService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param dormitoryId 记录ID
     */
    @GetMapping("/info/{dormitoryId}")
    public JsonResult info(@PathVariable("dormitoryId") Integer dormitoryId) {
        return dormitoryService.info(dormitoryId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "宿舍信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:dormitory:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Dormitory entity) {
        return dormitoryService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param dormitoryIds 记录ID
     */
    @Log(title = "宿舍信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:dormitory:drop")
    @DeleteMapping("/delete/{dormitoryIds}")
    public JsonResult delete(@PathVariable("dormitoryIds") Integer[] dormitoryIds) {
        return dormitoryService.deleteByIds(dormitoryIds);
    }

}