package com.withmore.shiro;

import com.withmore.shiro.auth.CustomModularRealmAuthenticator;
import com.withmore.shiro.auth.RealmAuthorizer;
import com.withmore.shiro.matcher.*;
import com.withmore.shiro.realm.*;
import com.withmore.shiro.token.WechatStudentUserEXAMToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ShiroConfig2")
public class ShiroConfig {

    /*salt*/
    @Value("${login.secret}")
    private String secretKey;

    // 注入Realm 适配器
    @Bean
    public ModularRealmAuthenticator customModularRealmAuthenticator() {
        CustomModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public WechatStudentRealm studentRealm() {
        WechatStudentRealm realm = new WechatStudentRealm();
        realm.setCredentialsMatcher(new StudentCredentialsMatcher());
        return realm;
    }

    @Bean
    public WechatStudentALRealm studentALRealm() {
        WechatStudentALRealm realm = new WechatStudentALRealm();
        realm.setCredentialsMatcher(new StudentALCredentialsMatcher());
        return realm;
    }

    @Bean
    public WechatStudentExamRealm studentExamRealm() {
        WechatStudentExamRealm realm = new WechatStudentExamRealm();
        realm.setCredentialsMatcher(new StudentEXAMCredentialsMatcher());
        return realm;
    }

    @Bean
    public WechatTeacherRealm teacherRealm() {
        WechatTeacherRealm teacherRealm = new WechatTeacherRealm();
        teacherRealm.setCredentialsMatcher(new TeacherCredentialsMatcher(secretKey));
        return teacherRealm;
    }

    @Bean
    public JwtTokenRealm jwtTokenRealm() {
        JwtTokenRealm realm = new JwtTokenRealm();
        // 注入自定义的JwtCredentialsMatcher校验器，否则会使用CustomCredentialsMatcher
        realm.setCredentialsMatcher(new JwtCredentialsMatcher());
        return realm;
    }

    @Bean
    public RealmAuthorizer realmAuthorizer() {
        return new RealmAuthorizer();
    }

}
