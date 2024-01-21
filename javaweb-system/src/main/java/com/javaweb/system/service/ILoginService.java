// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.LoginDto;
import com.javaweb.system.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台用户管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
public interface ILoginService extends IService<User> {

    /**
     * 获取验证码
     *
     * @param response 请求响应
     * @return
     */
    JsonResult captcha(HttpServletResponse response);

    /**
     * 用户登录
     *
     * @param loginDto 登录Dto
     * @return
     */
    JsonResult login(LoginDto loginDto, HttpServletRequest request);

    /**
     * 退出登录
     *
     * @return
     */
    JsonResult logout();

    /**
     * 根据名称获取对象
     *
     * @param username 名称
     * @return
     */
    User getUserByName(String username);

    /**
     * 系统登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User login(String username, String password);

}
