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
import com.javaweb.system.entity.Position;
import com.javaweb.system.query.PositionQuery;
import com.javaweb.system.service.IPositionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private IPositionService positionService;

    /**
     * 获取岗位列表
     *
     * @param positionQuery 查询条件
     */
    @RequiresPermissions("sys:position:index")
    @GetMapping("/index")
    public JsonResult index(PositionQuery positionQuery) {
        return positionService.getList(positionQuery);
    }

    /**
     * 添加岗位
     *
     * @param entity 实体对象
     */
    @Log(title = "岗位管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:position:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 编辑岗位
     *
     * @param entity 实体对象
     */
    @Log(title = "岗位管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:position:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 删除岗位
     *
     * @param positionIds 岗位ID
     */
    @Log(title = "岗位管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:position:delete")
    @DeleteMapping("/delete/{positionIds}")
    public JsonResult delete(@PathVariable("positionIds") Integer[] positionIds) {
        return positionService.deleteByIds(positionIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    @Log(title = "岗位管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:position:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody Position entity) {
        return positionService.setStatus(entity);
    }

    /**
     * 获取岗位列表
     *
     */
    @GetMapping("/getPositionList")
    public JsonResult getPositionList() {
        return positionService.getPositionList();
    }

}
