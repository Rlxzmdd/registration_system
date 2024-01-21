package com.withmore.shiro.token;

import com.withmore.user.wechat.dto.WechatStudentALLoginDto;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;


/**
 * 微信学生用户
 * 录取通知书编号+真实姓名登录Token
 */
@AllArgsConstructor
public class WechatStudentUserALToken implements AuthenticationToken {

    /*认证实体*/
    private WechatStudentALLoginDto token;

    @Override
    public Object getPrincipal() {
        return this.token.getSerialNumber();
    }

    @Override
    public Object getCredentials() {
        return this.token.getRealName();
    }

}
