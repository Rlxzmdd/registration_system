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

import com.javaweb.system.constant.ShiroConstants;
import com.javaweb.system.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CaptchaFilter extends AccessControlFilter {

    /**
     * 是否开启验证码
     */
    private boolean captchaEnabled = true;

    /**
     * 验证码类型
     */
    private String captchaType = "math";

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(ShiroConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(ShiroConstants.CURRENT_TYPE, captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 验证码禁用 或不是表单提交 允许访问
        if (captchaEnabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase())) {
            return true;
        }
        return validateResponse(httpServletRequest, httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE));
    }

    public boolean validateResponse(HttpServletRequest servletRequest, String validateCode) {
        Object obj = ShiroUtils.getSession().getAttribute("captcha");
        String code = String.valueOf(obj != null ? obj : "");
        if (StringUtils.isEmpty(validateCode) || !validateCode.equalsIgnoreCase(code)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute(ShiroConstants.CURRENT_CAPTCHA, ShiroConstants.CAPTCHA_ERROR);
        return true;
    }
}
