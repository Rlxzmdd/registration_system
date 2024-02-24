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
import com.javaweb.system.entity.Config;
import com.javaweb.system.query.ConfigQuery;
import com.javaweb.system.service.IConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService configService;

    /**
     * 获取配置列表
     *
     * @param configQuery 查询条件
     */
    @RequiresPermissions("sys:config:index")
    @GetMapping("/index")
    public JsonResult index(ConfigQuery configQuery) {
        return configService.getList(configQuery);
    }

    /**
     * 添加配置
     *
     * @param entity 实体对象
     */
    @Log(title = "配置管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:config:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Config entity) {
        return configService.edit(entity);
    }

    /**
     * 编辑配置
     *
     * @param entity 实体对象
     */
    @Log(title = "配置管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:config:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Config entity) {
        return configService.edit(entity);
    }

    /**
     * 删除配置
     *
     * @param configId 配置ID
     */
    @Log(title = "配置管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:config:delete")
    @DeleteMapping("/delete/{configId}")
    public JsonResult delete(@PathVariable("configId") Integer configId) {
        return configService.deleteById(configId);
    }

}
