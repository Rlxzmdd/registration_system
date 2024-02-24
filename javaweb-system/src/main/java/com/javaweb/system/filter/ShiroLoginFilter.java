// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.filter;

import com.alibaba.fastjson.JSONObject;
import com.javaweb.common.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登录过滤器
 */
@Slf4j
public class ShiroLoginFilter extends FormAuthenticationFilter {

    /**
     * 判断是否登录(已登录状态下不会走此方法)
     *
     * @param request
     * @param response
     * @param mappedValue
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String type = httpServletRequest.getHeader("wechat-mini-program");
        // 如是小程序用户请求 JwtFilter 已放行则表明验证已通过，如未携带则继续在此Filter中验证
        if (type != null && type.equals("wechat-mini-program")) {
            return true;
        }

        if (request instanceof HttpServletRequest) {
            if ("OPTIONS".equals(((HttpServletRequest) request).getMethod().toUpperCase())) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 是否是拒绝登录(没有登录的情况下会走此方法)
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        JsonResult jsonResult = new JsonResult();
        httpServletResponse.getWriter().write(JSONObject.toJSON(jsonResult.error(401, "请先登录")).toString());
        return false;
    }
}
