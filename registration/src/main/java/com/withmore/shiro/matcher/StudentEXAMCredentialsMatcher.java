package com.withmore.shiro.matcher;

import com.withmore.shiro.principal.WechatStudentExamCredentials;
import com.withmore.user.student.entity.Student;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class StudentEXAMCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 用户请求的登录凭据
        WechatStudentExamCredentials credentials = (WechatStudentExamCredentials) getCredentials(token);
        Student student = (Student) info.getPrincipals().getPrimaryPrincipal();
        int idCardNumberLength = student.getIdCard().length() ;
        String lastSixIdNumber = student.getIdCard()
                .toLowerCase()
                .substring(idCardNumberLength - 6, idCardNumberLength);
        return credentials.getLastIdNumber().equalsIgnoreCase(lastSixIdNumber)
                && credentials.getExamNumber().equalsIgnoreCase(student.getExamNumber());
    }
}
