// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义获取Token
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    //定义常量
    private static final String AUTHORIZATION = "token";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    //重写构造器
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 重写方法实现从请求头获取Token便于接口统一
     * 每次请求进来,Shiro会去从请求头找Authorization这个key对应的Value(Token)
     *
     * @param request
     * @param response
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中存在token 则从请求头中获取token
        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            // 这里禁用掉Cookie获取方式
            // 按默认规则从Cookie取Token
            // return super.getSessionId(request, response);
            return null;
        }
//        //将ServletRequest转换成HttpServletRequest
//        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
//        if (sessionId != null) {
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");
////
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
////            //automatically mark it valid here.  If it is invalid, the
////            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
//
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
//                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
//
//            //automatically mark it valid here.  If it is invalid, the
//            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
//            return sessionId;
//        } else {
//            return super.getSessionId(request, response);
//        }
    }
}
