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
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.query.AdSortQuery;
import com.javaweb.system.service.IAdSortService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 广告位管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/adsort")
public class AdSortController extends BaseController {

    @Autowired
    private IAdSortService adSortService;

    /**
     * 获取广告位列表
     *
     * @param adSortQuery 查询条件
     */
    @Log(title = "广告位管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:adsort:index")
    @GetMapping("/index")
    public JsonResult index(AdSortQuery adSortQuery) {
        return adSortService.getList(adSortQuery);
    }

    /**
     * 添加广告位
     *
     * @param entity 实体对象
     */
    @Log(title = "广告位管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:adsort:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
    }

    /**
     * 编辑广告位
     *
     * @param entity 实体对象
     */
    @Log(title = "广告位管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:adsort:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
    }

    /**
     * 删除广告位
     *
     * @param adSortIds 广告位ID
     */
    @Log(title = "广告位管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:adsort:delete")
    @DeleteMapping("/delete/{adSortIds}")
    public JsonResult delete(@PathVariable("adSortIds") Integer[] adSortIds) {
        return adSortService.deleteByIds(adSortIds);
    }

    /**
     * 获取广告位列表
     *
     */
    @GetMapping("/getAdSortList")
    public JsonResult getAdSortList() {
        return adSortService.getAdSortList();
    }

}
