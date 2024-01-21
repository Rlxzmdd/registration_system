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


import com.javaweb.common.config.CommonConfig;
import com.javaweb.system.common.BaseController;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.UpdatePwdDto;
import com.javaweb.system.dto.UpdateUserInfoDto;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.service.IMenuService;
import com.javaweb.system.service.IUserService;
import com.javaweb.system.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统主页 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserService userService;

    /**
     * 获取导航菜单
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public JsonResult getMenuList() {
        List<Menu> menuList = menuService.getMenuList(ShiroUtils.getUserId());
        return JsonResult.success(menuList);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public JsonResult getUserInfo() {
        return userService.getUserInfo();
    }

    /**
     * 修改密码
     *
     * @param updatePwdDto 参数
     * @return
     */
    @PutMapping("/updatePwd")
    public JsonResult updatePwd(@RequestBody UpdatePwdDto updatePwdDto) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return userService.updatePwd(updatePwdDto);
    }

    /**
     * 更新个人资料
     *
     * @param updateUserInfoDto 参数
     * @return
     */
    @PutMapping("/updateUserInfo")
    public JsonResult updateUserInfo(@RequestBody UpdateUserInfoDto updateUserInfoDto) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return userService.updateUserInfo(updateUserInfoDto);
    }
}
