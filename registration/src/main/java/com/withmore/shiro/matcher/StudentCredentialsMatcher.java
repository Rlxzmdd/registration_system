package com.withmore.shiro.matcher;

import com.withmore.shiro.token.WechatStudentUserToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class StudentCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        WechatStudentUserToken userToken = (WechatStudentUserToken) token;
        return equals(userToken.getCredentials(), getCredentials(info));
    }
}
