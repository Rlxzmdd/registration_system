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
import com.javaweb.system.entity.Link;
import com.javaweb.system.query.LinkQuery;
import com.javaweb.system.service.ILinkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 友链管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/link")
public class LinkController extends BaseController {

    @Autowired
    private ILinkService linkService;

    /**
     * 获取友链列表
     *
     * @param linkQuery 查询条件
     */
    @RequiresPermissions("sys:link:index")
    @GetMapping("/index")
    public JsonResult index(LinkQuery linkQuery) {
        return linkService.getList(linkQuery);
    }

    /**
     * 添加友链
     *
     * @param entity 实体对象
     */
    @Log(title = "友链管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:link:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Link entity) {
        return linkService.edit(entity);
    }

    /**
     * 编辑友链
     *
     * @param entity 实体对象
     */
    @Log(title = "友链管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:link:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Link entity) {
        return linkService.edit(entity);
    }

    /**
     * 删除友链
     *
     * @param linkIds 友链ID
     */
    @Log(title = "友链管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:link:delete")
    @DeleteMapping("/delete/{linkIds}")
    public JsonResult delete(@PathVariable("linkIds") Integer[] linkIds) {
        return linkService.deleteByIds(linkIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    @Log(title = "友链管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:link:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody Link entity) {
        return linkService.setStatus(entity);
    }

}
