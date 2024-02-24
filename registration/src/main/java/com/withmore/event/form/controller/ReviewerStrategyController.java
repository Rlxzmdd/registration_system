package com.withmore.event.form.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import com.withmore.event.form.entity.ReviewerStrategy;
import com.withmore.event.form.query.ReviewerStrategyQuery;
import com.withmore.event.form.service.IReviewerStrategyService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 表单审核策略表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/reviewer_strategy")
public class ReviewerStrategyController extends BaseController {

    @Autowired
    private IReviewerStrategyService reviewerStrategyService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:reviewer_strategy:index")
    @GetMapping("/index")
    public JsonResult index(ReviewerStrategyQuery query) {
        return reviewerStrategyService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "表单审核策略表", logType = LogType.INSERT)
    @RequiresPermissions("sys:reviewer_strategy:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ReviewerStrategy entity) {
        return reviewerStrategyService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return reviewerStrategyService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "表单审核策略表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:reviewer_strategy:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ReviewerStrategy entity) {
        return reviewerStrategyService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "表单审核策略表", logType = LogType.DELETE)
    @RequiresPermissions("sys:reviewer_strategy:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return reviewerStrategyService.deleteByIds(Ids);
    }

}