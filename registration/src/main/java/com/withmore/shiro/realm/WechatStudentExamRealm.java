package com.withmore.shiro.realm;

import com.javaweb.common.exception.user.CaptchaException;
import com.javaweb.common.exception.user.UserNotExistsException;
import com.withmore.shiro.principal.WechatStudentExamCredentials;
import com.withmore.shiro.token.WechatStudentUserEXAMToken;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WechatStudentExamRealm extends AuthorizingRealm {

    @Autowired
    private IStudentService studentService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof WechatStudentUserEXAMToken;
    }

    /**
     * 授权，此Realm只负责校验录取通知书+姓名的凭据，并返回Token
     * 授权操作在TokenRealm中实现
     *
     * @param principalCollection
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken Token
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String realName = String.valueOf(authenticationToken.getPrincipal());
        WechatStudentExamCredentials credentials = (WechatStudentExamCredentials) (authenticationToken.getCredentials());

        Student student = null;
        try {
            student = studentService.loginByExamNumber(credentials.getExamNumber(), realName);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (LockedAccountException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (ExcessiveAttemptsException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对考生号对应编号用户[" + credentials.getExamNumber() + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }

        // 创建验证信息对象
        return new SimpleAuthenticationInfo(
                student,
                credentials,
                getName()
        );
    }
}
