package com.withmore.shiro.token;

import com.withmore.shiro.principal.WechatStudentExamCredentials;
import com.withmore.user.wechat.dto.WechatStudentEXAMLoginDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
@AllArgsConstructor
public class WechatStudentUserEXAMToken implements AuthenticationToken {
    private WechatStudentEXAMLoginDto dto;

    @Override
    public Object getPrincipal() {
        return dto.getRealName();
    }

    @Override
    public Object getCredentials() {
        return WechatStudentExamCredentials
                .builder()
                .examNumber(dto.getExamNumber())
                .lastIdNumber(dto.getLastIdNumber())
                .build();
    }
}
