package com.javaweb.system.shiro.storage;

import com.javaweb.system.utils.UserAgentUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class CustomSessionStorage extends DefaultWebSessionStorageEvaluator {
    @Override
    public boolean isSessionStorageEnabled(Subject subject) {
        if (subject instanceof WebSubject) {
            HttpServletRequest request = (HttpServletRequest) ((WebSubject) subject).getServletRequest();
            if (UserAgentUtil.isWechatProgram(request)) {
                return false;
            }
        }
        return super.isSessionStorageEnabled(subject);
    }
}
