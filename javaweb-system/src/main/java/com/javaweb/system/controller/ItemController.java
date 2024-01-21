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
import com.javaweb.system.entity.Item;
import com.javaweb.system.query.ItemQuery;
import com.javaweb.system.service.IItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 站点配置表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private IItemService itemService;

    /**
     * 获取站点列表
     *
     * @param itemQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:item:index")
    @GetMapping("/index")
    public JsonResult index(ItemQuery itemQuery) {
        return itemService.getList(itemQuery);
    }

    /**
     * 添加站点
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:item:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Item entity) {
        return itemService.edit(entity);
    }

    /**
     * 编辑站点
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:item:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Item entity) {
        return itemService.edit(entity);
    }

    /**
     * 删除站点
     *
     * @param itemIds 站点ID
     * @return
     */
    @Log(title = "站点管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:item:delete")
    @DeleteMapping("/delete/{itemIds}")
    public JsonResult delete(@PathVariable("itemIds") Integer[] itemIds) {
        return itemService.deleteByIds(itemIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:item:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody Item entity) {
        return itemService.setStatus(entity);
    }

    /**
     * 获取站点列表
     *
     * @return
     */
    @GetMapping("/getItemList")
    public JsonResult getItemList() {
        return JsonResult.success(itemService.getItemList());
    }

}
