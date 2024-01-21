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
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.ResetPwdDto;
import com.javaweb.system.entity.User;
import com.javaweb.system.query.UserQuery;
import com.javaweb.system.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 后台用户管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     *
     * @param userQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:user:index")
    @GetMapping("/index")
    public JsonResult index(UserQuery userQuery) {
        return userService.getList(userQuery);
    }

    /**
     * 添加用户
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "用户管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:user:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody User entity) {
        return userService.edit(entity);
    }

    /**
     * 编辑用户
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "用户管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:user:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody User entity) {
        return userService.edit(entity);
    }

    /**
     * 删除用户
     *
     * @param userIds 用户ID
     * @return
     */
    @Log(title = "用户管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:user:delete")
    @DeleteMapping("/delete/{userIds}")
    public JsonResult delete(@PathVariable("userIds") Integer[] userIds) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return userService.deleteByIds(userIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "用户管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:user:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody User entity) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return userService.setStatus(entity);
    }

    /**
     * 重置密码
     *
     * @param resetPwdDto 参数
     * @return
     */
    @Log(title = "用户管理", logType = LogType.RESETPWD)
    @RequiresPermissions("sys:user:resetPwd")
    @PutMapping("/resetPwd")
    public JsonResult resetPwd(@RequestBody ResetPwdDto resetPwdDto) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return userService.resetPwd(resetPwdDto);
    }

}
