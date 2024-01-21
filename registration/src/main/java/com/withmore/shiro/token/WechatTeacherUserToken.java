package com.withmore.shiro.token;

import com.withmore.user.wechat.dto.WechatTeacherLoginDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.shiro.authc.AuthenticationToken;

@Builder
@AllArgsConstructor
public class WechatTeacherUserToken implements AuthenticationToken {

    private WechatTeacherLoginDto dto;

    @Override
    public Object getPrincipal() {
        return dto.getTchNumber();
    }

    @Override
    public Object getCredentials() {
        return dto.getPassword();
    }
}
