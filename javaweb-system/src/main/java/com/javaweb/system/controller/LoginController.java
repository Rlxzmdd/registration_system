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

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.dto.LoginDto;
import com.javaweb.system.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 系统登录 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private ILoginService loginService;

    /**
     * 获取验证码
     *
     * @param response 网络请求
     */
    @GetMapping("/captcha")
    public JsonResult captcha(HttpServletResponse response) {
        return loginService.captcha(response);
    }

    /**
     * 系统登录
     *
     * @param loginDto 参数
     * @param request  网络请求
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return loginService.login(loginDto, request);
    }

    /**
     * 退出登录
     *
     */
    @GetMapping("/logout")
    public JsonResult logout() {
        return loginService.logout();
    }

    /**
     * 用户未登录
     *
     */
    @GetMapping("/un_auth")
    public JsonResult unAuth() {
        return JsonResult.error(HttpStatus.UNAUTHORIZED, "");
    }

    /**
     * 用户无权限
     *
     */
    @GetMapping("/unauthorized")
    public JsonResult unauthorized() {
        return JsonResult.error(HttpStatus.FORBIDDEN, "用户无权限");
    }

}
