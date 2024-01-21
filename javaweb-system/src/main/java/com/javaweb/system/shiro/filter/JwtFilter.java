package com.javaweb.system.shiro.filter;

import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.shiro.Token.WechatUserRequestToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpHeaders;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT token 过滤器，验证小程序用户请求的Token
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        // HttpHeader 携带此参数确定为小程序发起，由此filter拦截，验证成功下一个filter将直接通过
        String type = httpServletRequest.getHeader("wechat-mini-program");
        // 是否为小程序发起的请求，不是直接到下一个Filter进行身份验证
        if (token == null || type == null) {
            return true;
        }

        // 处理Bearer Token
        String[] split = token.split(" ");
        if (split.length > 1) {
            token = split[1];
        } else {
            // Token 验证授权出现异常情况
            JsonResultS.writeToResponse(httpServletResponse, JsonResultS.error(ResultCodeEnum.USER_ERROR_A0305), 401);
            return false;
        }

        // 传递给JwtTokenRealm 进行认证与授权
        WechatUserRequestToken jwtToken = new WechatUserRequestToken(token);
        try {
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
        } catch (AuthenticationException e) {
            // Token 验证授权出现异常情况
            JsonResultS.writeToResponse(httpServletResponse, JsonResultS.error(ResultCodeEnum.USER_ERROR_A0200), 401);
            return false;
        }
        return true;
    }
}
