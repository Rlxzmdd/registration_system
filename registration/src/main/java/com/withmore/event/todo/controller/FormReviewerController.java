// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.event.todo.entity.FormReviewer;
import com.withmore.event.todo.query.FormReviewerQuery;
import com.withmore.event.todo.service.IFormReviewerService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审核人映射表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/form_reviewer")
public class FormReviewerController extends BaseController {

    @Autowired
    private IFormReviewerService formReviewerService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:form_reviewer:index")
    @GetMapping("/index")
    public JsonResult index(FormReviewerQuery query) {
        return formReviewerService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "审核人映射表", logType = LogType.INSERT)
    @RequiresPermissions("sys:form_reviewer:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody FormReviewer entity) {
        return formReviewerService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return formReviewerService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "审核人映射表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:form_reviewer:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody FormReviewer entity) {
        return formReviewerService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "审核人映射表", logType = LogType.DELETE)
    @RequiresPermissions("sys:form_reviewer:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return formReviewerService.deleteByIds(Ids);
    }

}