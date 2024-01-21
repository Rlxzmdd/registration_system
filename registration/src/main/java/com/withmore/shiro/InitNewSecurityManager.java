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
    @Autowired
    private SecurityManager securityManager;

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
        realmList.add(studentRealm);
        realmList.add(studentALRealm);
        realmList.add(studentExamRealm);
        realmList.add(teacherRealm);
        realmList.add(jwtTokenRealm);
        realmAuthorizer.setRealms(realmList);

        DefaultSecurityManager manager = (DefaultSecurityManager) securityManager;
        manager.setAuthenticator(customModularRealmAuthenticator);
        manager.setAuthorizer(realmAuthorizer);
        // 加载顺序 Authenticator -> realms

        manager.setRealms(realmList);
    }
}
