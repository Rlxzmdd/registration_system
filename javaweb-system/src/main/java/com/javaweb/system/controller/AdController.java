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
import com.javaweb.system.entity.Ad;
import com.javaweb.system.query.AdQuery;
import com.javaweb.system.service.IAdService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 广告管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/ad")
public class AdController extends BaseController {

    @Autowired
    private IAdService adService;

    /**
     * 获取广告列表
     *
     * @param adQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:ad:index")
    @GetMapping("/index")
    public JsonResult index(AdQuery adQuery) {
        return adService.getList(adQuery);
    }

    /**
     * 添加广告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:ad:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Ad entity) {
        return adService.edit(entity);
    }

    /**
     * 编辑广告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:ad:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Ad entity) {
        return adService.edit(entity);
    }

    /**
     * 删除广告
     *
     * @param adIds 广告ID
     * @return
     */
    @Log(title = "广告管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:ad:delete")
    @DeleteMapping("/delete/{adIds}")
    public JsonResult delete(@PathVariable("adIds") Integer[] adIds) {
        return adService.deleteByIds(adIds);
    }

}
