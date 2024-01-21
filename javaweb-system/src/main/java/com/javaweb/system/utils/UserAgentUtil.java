package com.javaweb.system.utils;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtil {
    public static boolean isWechatProgram(HttpServletRequest request) {
        return request.getHeader("wechat-mini-program") != null;
    }
}
