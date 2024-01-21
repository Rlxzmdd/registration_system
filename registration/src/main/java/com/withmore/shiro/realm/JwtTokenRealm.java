package com.withmore.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.shiro.Token.WechatUserRequestToken;
import com.withmore.shiro.principal.WechatProgramUserPrincipal;
import com.withmore.user.role.mapper.WechatMiniProgramRoleMapper;
import com.withmore.common.constant.Constant;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.service.IStudentService;
import com.withmore.user.teacher.entity.Teacher;
import com.withmore.user.teacher.service.ITeacherService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JwtTokenRealm extends AuthorizingRealm {

    /*通过Bean聚合*/
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private WechatMiniProgramRoleMapper wechatMiniProgramRoleMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof WechatUserRequestToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 在doGetAuthenticationInfo 方法成功后，将携带credentials 执行此方法
        WechatProgramUserPrincipal wechatProgramUserPrincipal = (WechatProgramUserPrincipal) principals.getPrimaryPrincipal();
        String number = wechatProgramUserPrincipal.getNumber();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissionList = wechatMiniProgramRoleMapper.getPermissionList(number, wechatProgramUserPrincipal.getType());
        for (String permission : permissionList) {
            if (!StringUtils.isEmpty(permission)) {
                authorizationInfo.addStringPermission(permission);
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证用户的Token
     *
     * @param token 用户认证Token ，类型为WechatStudentUserRequestToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        WechatUserRequestToken requestToken = (WechatUserRequestToken) token;
        String userToken = String.valueOf(requestToken.getCredentials());
        if (userToken == null) {
            throw new AuthenticationException(ResultCodeEnum.USER_ERROR_A0303.getDescription());
        }
        // 根据生成密钥验证Token真伪与有效性
        if (!jwtUtil.verify(userToken)) {
            throw new AuthenticationException(ResultCodeEnum.USER_ERROR_A0304.getDescription());
        }
        String type = jwtUtil.getUserType(userToken);
        String user_id = jwtUtil.getUserId(userToken);
        // Token 中未携带用户类型情况
        if (type == null || user_id == null) {
            throw new AuthenticationException(ResultCodeEnum.USER_ERROR_A0300.getDescription());
        }

        WechatProgramUserPrincipal wechatProgramUserPrincipal;
        if (Constant.TOKEN_USER_TYPE_STUDENT.equals(type)) {
            QueryWrapper<Student> wrapper = new QueryWrapper<>();
            wrapper.eq("stu_number", user_id);
            wrapper.eq("mark", 1);
            Student student = studentService.getOne(wrapper);
            if (student == null) {
                throw new UnknownAccountException(ResultCodeEnum.USER_ERROR_A0201.getDescription());
            }
            wechatProgramUserPrincipal = new WechatProgramUserPrincipal().setNumber(student.getStuNumber()).setType(Constant.TOKEN_USER_TYPE_STUDENT);
        } else if (Constant.TOKEN_USER_TYPE_TEACHER.equals(type)) {
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.eq("tch_number", user_id);
            wrapper.eq("mark", 1);
            Teacher teacher = teacherService.getOne(wrapper);
            if (teacher == null) {
                throw new UnknownAccountException(ResultCodeEnum.USER_ERROR_A0201.getDescription());
            }
            wechatProgramUserPrincipal = new WechatProgramUserPrincipal().setNumber(teacher.getTchNumber()).setType(Constant.TOKEN_USER_TYPE_TEACHER);
        } else {
            throw new AuthenticationException(ResultCodeEnum.USER_ERROR_A0305.getDescription());
        }

        return new SimpleAuthenticationInfo(wechatProgramUserPrincipal, token, getName());
    }


}
