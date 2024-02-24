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
import com.javaweb.system.entity.Dict;
import com.javaweb.system.query.DictQuery;
import com.javaweb.system.service.IDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-01
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    /**
     * 获取字典列表
     *
     * @param dictQuery 查询条件
     */
    @RequiresPermissions("sys:dictionary:index")
    @GetMapping("/index")
    public JsonResult index(DictQuery dictQuery) {
        return dictService.getList(dictQuery);
    }

    /**
     * 添加字典
     *
     * @param entity 实体对象
     */
    @Log(title = "字典分组", logType = LogType.INSERT)
    @RequiresPermissions("sys:dictionary:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dict entity) {
        return dictService.edit(entity);
    }

    /**
     * 编辑字典
     *
     * @param entity 实体对象
     */
    @Log(title = "字典分组", logType = LogType.UPDATE)
    @RequiresPermissions("sys:dictionary:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Dict entity) {
        return dictService.edit(entity);
    }

    /**
     * 删除字典
     *
     * @param dicTypeId 字典ID
     */
    @Log(title = "字典分组", logType = LogType.DELETE)
    @RequiresPermissions("sys:dictionary:delete")
    @DeleteMapping("/delete/{dicTypeId}")
    public JsonResult delete(@PathVariable("dicTypeId") Integer dicTypeId) {
        return dictService.deleteById(dicTypeId);
    }

}
