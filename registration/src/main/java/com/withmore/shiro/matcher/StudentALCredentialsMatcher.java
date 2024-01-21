package com.withmore.shiro.matcher;

import com.withmore.shiro.token.WechatStudentUserALToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class StudentALCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        WechatStudentUserALToken userToken = (WechatStudentUserALToken) token;
        Object credentials = getCredentials(info);
        return equals(userToken.getCredentials(), credentials);
    }
}
