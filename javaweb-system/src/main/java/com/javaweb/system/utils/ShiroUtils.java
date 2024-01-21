// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.utils;

import com.javaweb.common.utils.SpringUtils;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * Shiro工具类
 */
public class ShiroUtils {

    /**
     * 私有构造器
     **/
    private ShiroUtils() {
    }

    private static RedisSessionDAO redisSessionDAO = SpringUtils.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户Session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户退出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static User getUserInfo() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户编号
     *
     * @return
     */
    public static Integer getUserId() {
        User user = getUserInfo();
        return user.getId();
    }

}
