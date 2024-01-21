package com.javaweb.system.shiro.factory;

import com.javaweb.system.utils.UserAgentUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.subject.WebSubjectContext;

import javax.servlet.http.HttpServletRequest;

public class MyselfSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //没有经过shiro filter的请求，无法强转为webSubjectContext。涉及到shiro自带的包装
        if (!(context instanceof WebSubjectContext)) {
            return super.createSubject(context);
        }
        WebSubjectContext webSubjectContext = (WebSubjectContext) context;
        HttpServletRequest request = (HttpServletRequest) webSubjectContext.getServletRequest();
        //登录状态下，不创建session不保存用户信息。 如果不加这个，会导致request为null，这个有疑点，探究后再来完善。
        if (!context.isAuthenticated()) {
            if (UserAgentUtil.isWechatProgram(request)) {
                //不创建session
                context.setSessionCreationEnabled(false);
            } else {
                //创建session
                context.setSessionCreationEnabled(true);

            }
        }
        return super.createSubject(context);
    }

}
