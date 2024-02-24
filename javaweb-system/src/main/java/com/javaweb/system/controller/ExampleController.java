// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.entity.Example;
import com.javaweb.system.query.ExampleQuery;
import com.javaweb.system.service.IExampleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 演示案例表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2021-03-27
 */
@RestController
@RequestMapping("/example")
public class ExampleController extends BaseController {

    @Autowired
    private IExampleService exampleService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:example:index")
    @GetMapping("/index")
    public JsonResult index(ExampleQuery query) {
        return exampleService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "演示案例表", logType = LogType.INSERT)
    @RequiresPermissions("sys:example:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Example entity) {
        return exampleService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param exampleId 记录ID
     */
    @GetMapping("/info/{exampleId}")
    public JsonResult info(@PathVariable("exampleId") Integer exampleId) {
        return exampleService.info(exampleId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "演示案例表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:example:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Example entity) {
        return exampleService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param exampleIds 记录ID
     */
    @Log(title = "演示案例表", logType = LogType.DELETE)
    @RequiresPermissions("sys:example:drop")
    @DeleteMapping("/delete/{exampleIds}")
    public JsonResult delete(@PathVariable("exampleIds") Integer[] exampleIds) {
        return exampleService.deleteByIds(exampleIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    @Log(title = "演示案例表", logType = LogType.STATUS)
    @RequiresPermissions("sys:example:status")
    @PutMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Example entity) {
        return exampleService.setStatus(entity);
    }
}