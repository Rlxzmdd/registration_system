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
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/operlog")
public class OperLogController extends BaseController {

    @Autowired
    private IOperLogService operLogService;

    /**
     * 获取操作日志列表
     *
     * @param operLogQuery 查询条件
     */
    @RequiresPermissions("sys:operlog:index")
    @GetMapping("/index")
    public JsonResult index(OperLogQuery operLogQuery) {
        return operLogService.getList(operLogQuery);
    }

    /**
     * 删除操作日志
     *
     * @param operLogId 操作日志ID
     */
    @Log(title = "操作日志", logType = LogType.DELETE)
    @RequiresPermissions("sys:operlog:delete")
    @DeleteMapping("/delete/{operLogId}")
    public JsonResult delete(@PathVariable("operLogId") Integer operLogId) {
        return operLogService.deleteById(operLogId);
    }

}
