package com.withmore.shiro;

import com.javaweb.system.shiro.MyShiroRealm;
import com.withmore.shiro.auth.RealmAuthorizer;
import com.withmore.shiro.realm.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.shiro.mgt.SecurityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 在Bean 加载完成后获取Shiro securityManager 注入registration 模块中自定义的realm
 *
 * @author Cheney
 */

@Component
@Order(2)
@Slf4j
public class InitNewSecurityManager implements CommandLineRunner {
    // 自动注入，无需实例化
    @Autowired
    private SecurityManager securityManager;

    // -> com.withmore.shiro.auth.CustomModularRealmAuthenticator
    @Autowired
    private ModularRealmAuthenticator customModularRealmAuthenticator;

    @Autowired
    private RealmAuthorizer realmAuthorizer;

    @Autowired
    private WechatStudentRealm studentRealm;

    @Autowired
    private WechatStudentALRealm studentALRealm;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Autowired
    private JwtTokenRealm jwtTokenRealm;

    @Autowired
    private WechatTeacherRealm teacherRealm;

    @Autowired
    private WechatStudentExamRealm studentExamRealm;

    @Override
    public void run(String... args) {
        // 注入Realm 到 Shiro SecurityManager 中
        List<Realm> realmList = new ArrayList<>();
        realmList.add(myShiroRealm);
        // 学生三类登录验证方式
        realmList.add(studentRealm);
        realmList.add(studentALRealm);
        realmList.add(studentExamRealm);
        // 教师工号登录验证方式
        realmList.add(teacherRealm);
        // Token常态验证方式
        realmList.add(jwtTokenRealm);

        realmAuthorizer.setRealms(realmList);
        DefaultSecurityManager manager = (DefaultSecurityManager) securityManager;
        // 设置自定义的认证器
        manager.setAuthenticator(customModularRealmAuthenticator);
        // 设置自定义的授权器
        manager.setAuthorizer(realmAuthorizer);
        // 加载顺序 Authenticator -> realms
        // Authenticator和Authorizer的区别 -> Authenticator是用来验证用户身份的，而Authorizer是用来验证用户权限的
        manager.setRealms(realmList);
    }
}
