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
import com.javaweb.common.utils.ExcelUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.entity.Level;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.service.ILevelService;
import com.javaweb.system.vo.level.LevelInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 职级表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/level")
public class LevelController extends BaseController {

    @Autowired
    private ILevelService levelService;

    /**
     * 获取职级列表
     *
     * @param levelQuery 查询条件
     */
    @RequiresPermissions("sys:level:index")
    @GetMapping("/index")
    public JsonResult index(LevelQuery levelQuery) {
        return levelService.getList(levelQuery);
    }

    /**
     * 添加职级
     *
     * @param entity 实体对象
     */
    @Log(title = "职级管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:level:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 编辑职级
     *
     * @param entity 实体对象
     */
    @Log(title = "职级管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:level:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 删除职级
     *
     * @param levelIds 职级ID
     */
    @Log(title = "职级管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:level:delete")
    @DeleteMapping("/delete/{levelIds}")
    public JsonResult delete(@PathVariable("levelIds") Integer[] levelIds) {
        return levelService.deleteByIds(levelIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    @Log(title = "职级管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:level:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody Level entity) {
        return levelService.setStatus(entity);
    }

    /**
     * 获取职级列表
     *
     */
    @GetMapping("/getLevelList")
    public JsonResult getLevelList() {
        return levelService.getLevelList();
    }

    /**
     * 导出Excel
     *
     * @param levelQuery 查询条件
     */
    @PostMapping("/exportExcel")
    public JsonResult exportExcel(@RequestBody LevelQuery levelQuery) {
        List<LevelInfoVo> levelInfoVoList = levelService.exportExcel(levelQuery);
        ExcelUtils<LevelInfoVo> excelUtils = new ExcelUtils<LevelInfoVo>(LevelInfoVo.class);
        return excelUtils.exportExcel(levelInfoVoList, "职级列表");
    }

}
