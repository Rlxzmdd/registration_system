package com.javaweb.system.controller;

import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.service.IConfigWebService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/configweb")
public class ConfigWebController extends BaseController {

    @Autowired
    private IConfigWebService configWebService;

    /**
     * 获取配置列表
     *
     */
    @RequiresPermissions("sys:website:index")
    @GetMapping("/index")
    public JsonResult index() {
        return configWebService.getList();
    }

    /**
     * 保存配置信息
     *
     * @param info 表单信息
     */
    @RequiresPermissions("sys:website:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Map<String, Object> info) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return configWebService.edit(info);
    }

}
