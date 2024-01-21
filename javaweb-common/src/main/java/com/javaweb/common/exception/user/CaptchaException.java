// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.common.exception.user;

/**
 * 验证码异常处理类
 */
public class CaptchaException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }

}
