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
import com.javaweb.system.query.LoginLogQuery;
import com.javaweb.system.service.ILoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/loginlog")
public class LoginLogController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 获取登录日志列表
     *
     * @param loginLogQuery 查询条件
     */
    @RequiresPermissions("sys:loginlog:index")
    @GetMapping("/index")
    public JsonResult index(LoginLogQuery loginLogQuery) {
        return loginLogService.getList(loginLogQuery);
    }

    /**
     * 删除登录日志
     *
     * @param loginLogIds 登录日志ID
     */
    @Log(title = "登录日志", logType = LogType.DELETE)
    @RequiresPermissions("sys:loginlog:delete")
    @DeleteMapping("/delete/{loginLogIds}")
    public JsonResult delete(@PathVariable("loginLogIds") Integer[] loginLogIds) {
        return loginLogService.deleteByIds(loginLogIds);
    }

}
