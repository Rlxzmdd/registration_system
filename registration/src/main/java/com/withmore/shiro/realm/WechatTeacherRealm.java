package com.withmore.shiro.realm;

import com.javaweb.common.exception.user.CaptchaException;
import com.javaweb.common.exception.user.UserNotExistsException;
import com.withmore.shiro.token.WechatTeacherUserToken;
import com.withmore.user.teacher.entity.Teacher;
import com.withmore.user.teacher.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WechatTeacherRealm extends AuthorizingRealm {

    @Autowired
    private ITeacherService teacherService;

    /**
     * 支持Token 适配
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof WechatTeacherUserToken;
    }

    /**
     * 授权，此Realm只负责校验工号+密码的凭据，并返回Token
     * 授权操作在TokenRealm中实现
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tchNumber = String.valueOf(authenticationToken.getPrincipal());
        String password = String.valueOf(authenticationToken.getCredentials());
        Teacher teacher = null;
        try {
            teacher = teacherService.login(tchNumber, password);
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
            log.info("对教师用户[" + tchNumber + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }

        // 创建验证信息对象
        return new SimpleAuthenticationInfo(
                teacher,
                teacher.getPassword(),
                getName()
        );
    }
}
