// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.shiro;

import com.javaweb.common.exception.user.CaptchaException;
import com.javaweb.common.exception.user.UserNotExistsException;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.User;
import com.javaweb.system.mapper.MenuMapper;
import com.javaweb.system.service.ILoginService;
import com.javaweb.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("认证成功进行授权中...");
        User user = (User) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Integer userId = user.getId();
        if (userId.equals(1)) {
            //管理员拥有所有权限
            simpleAuthorizationInfo.addStringPermission("*:*");
        } else {
            List<String> permissionList = menuMapper.getPermissionList(userId);
            if (!permissionList.isEmpty()) {
                for (String permission : permissionList) {
                    if (StringUtils.isEmpty(permission)) {
                        continue;
                    }
                    simpleAuthorizationInfo.addStringPermission(permission);
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行身份认证中...");
        // 获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }

        User user = null;
        try {
            user = loginService.login(username, password);
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
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,                                  //用户名
                user.getPassword(),                    //密码
                ByteSource.Util.bytes(""),      //设置盐值
                getName()
        );
        return authenticationInfo;
    }
}
