package com.withmore.shiro.matcher;

import com.javaweb.common.utils.CommonUtils;
import com.withmore.shiro.token.WechatTeacherUserToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.nio.charset.StandardCharsets;

public class TeacherCredentialsMatcher extends SimpleCredentialsMatcher {

    /*加密盐*/
    private String salt;

    public TeacherCredentialsMatcher(String salt) {
        super();
        this.salt = salt;
    }

    /**
     * 密码凭据校验
     * 需前端MD5 32位加密后传递
     *
     * @param token 认证Token
     * @param info  用户信息
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        WechatTeacherUserToken userToken = (WechatTeacherUserToken) token;
        Object accountCredentials = getCredentials(info);
        // 用户提交的密码
        String formCredentials = String.valueOf(userToken.getCredentials());
        if (accountCredentials == null || formCredentials == null) {
            return false;
        }
        String md5 = CommonUtils.md5(formCredentials.getBytes(StandardCharsets.UTF_8));
        String pwd = CommonUtils.md5((md5 + salt).getBytes(StandardCharsets.UTF_8));
        if (pwd == null) {
            return false;
        }
        pwd = pwd.toUpperCase();
        return equals(accountCredentials, pwd);
    }
}
