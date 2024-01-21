// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.major.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.group.major.entity.Major;
import com.withmore.group.major.query.MajorQuery;
import com.withmore.group.major.service.IMajorService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 专业信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/major")
public class MajorController extends BaseController {

    @Autowired
    private IMajorService majorService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:major:index")
    @GetMapping("/index")
    public JsonResult index(MajorQuery query) {
        return majorService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "专业信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:major:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Major entity) {
        return majorService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param majorId 记录ID
     * @return
     */
    @GetMapping("/info/{majorId}")
    public JsonResult info(@PathVariable("majorId") Integer majorId) {
        return majorService.info(majorId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "专业信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:major:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Major entity) {
        return majorService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param majorIds 记录ID
     * @return
     */
    @Log(title = "专业信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:major:drop")
    @DeleteMapping("/delete/{majorIds}")
    public JsonResult delete(@PathVariable("majorIds") Integer[] majorIds) {
        return majorService.deleteByIds(majorIds);
    }

}