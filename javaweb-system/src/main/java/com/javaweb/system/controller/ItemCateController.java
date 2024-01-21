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
import com.javaweb.system.entity.ItemCate;
import com.javaweb.system.query.ItemCateQuery;
import com.javaweb.system.service.IItemCateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 栏目管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/itemcate")
public class ItemCateController extends BaseController {

    @Autowired
    private IItemCateService itemCateService;

    /**
     * 获取栏目列表
     *
     * @param itemCateQuery 查询条件
     * @return
     */
    @Log(title = "栏目管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:itemcate:index")
    @GetMapping("/index")
    public JsonResult index(ItemCateQuery itemCateQuery) {
        return itemCateService.getList(itemCateQuery);
    }

    /**
     * 添加栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "栏目管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:itemcate:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ItemCate entity) {
        return itemCateService.edit(entity);
    }

    /**
     * 编辑栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "栏目管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:itemcate:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ItemCate entity) {
        return itemCateService.edit(entity);
    }

    /**
     * 删除栏目
     *
     * @param itemCateId 栏目ID
     * @return
     */
    @Log(title = "栏目管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:itemcate:delete")
    @DeleteMapping("/delete/{itemCateId}")
    public JsonResult delete(@PathVariable("itemCateId") Integer itemCateId) {
        return itemCateService.deleteById(itemCateId);
    }

}
