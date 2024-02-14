// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.utils.QRCodeUtils;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.dto.WechatProgramIdentityDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.common.utils.WxUserRegisterUtil;
import com.withmore.shiro.token.WechatStudentUserALToken;
import com.withmore.shiro.token.WechatStudentUserEXAMToken;
import com.withmore.shiro.token.WechatStudentUserToken;
import com.withmore.shiro.token.WechatTeacherUserToken;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.mapper.StudentMapper;
import com.withmore.user.student.vo.student.UserSimpleVo;
import com.withmore.user.teacher.entity.Teacher;
import com.withmore.user.teacher.mapper.TeacherMapper;
import com.withmore.user.wechat.dto.WechatStudentALLoginDto;
import com.withmore.user.wechat.dto.WechatStudentEXAMLoginDto;
import com.withmore.user.wechat.dto.WechatStudentLoginDto;
import com.withmore.user.wechat.dto.WechatTeacherLoginDto;
import com.withmore.user.wechat.entity.Wechat;
import com.withmore.user.wechat.mapper.WechatMapper;
import com.withmore.user.wechat.query.WechatQuery;
import com.withmore.user.wechat.service.IWechatService;
import com.withmore.user.wechat.vo.wechat.WechatInfoVo;
import com.withmore.user.wechat.vo.wechat.WechatListVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 微信小程序用户表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-18
 */
@Service
public class WechatServiceImpl extends BaseServiceImpl<WechatMapper, Wechat> implements IWechatService {

    /*是否允许覆盖绑定 ，开发环境下允许*/
    @Value("${wechat.program.coverBind}")
    private boolean coverBind;

    @Autowired
    private WechatMapper wechatMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WxUserRegisterUtil wxUserRegisterUtil;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        WechatQuery wechatQuery = (WechatQuery) query;
        // 查询条件
        QueryWrapper<Wechat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Wechat> page = new Page<>(wechatQuery.getPage(), wechatQuery.getLimit());
        IPage<Wechat> pageData = wechatMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            WechatListVo wechatListVo = Convert.convert(WechatListVo.class, x);
            return wechatListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Wechat entity = (Wechat) super.getInfo(id);
        // 返回视图Vo
        WechatInfoVo wechatInfoVo = new WechatInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, wechatInfoVo);
        return wechatInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Wechat entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Wechat entity) {
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS loginByStuNumberAndRealName(WechatStudentLoginDto loginDto) {
        if (StringUtils.isEmpty(loginDto.getStuNumber()) || StringUtils.isEmpty(loginDto.getRealName())) {
            return JsonResultS.error("学号或真实姓名为空");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            WechatStudentUserToken userToken = new WechatStudentUserToken(loginDto);
            subject.login(userToken);
        } catch (UnknownAccountException e) {
            return JsonResultS.error("未知学号");
        } catch (IncorrectCredentialsException e) {
            return JsonResultS.error("真实姓名不正确");
        } catch (LockedAccountException e) {
            return JsonResultS.error("账号已锁定");
        } catch (ExcessiveAttemptsException e) {
            return JsonResultS.error("学号或真实姓名输入错误次数过多");
        } catch (AuthenticationException e) {
            return JsonResultS.error("学号或真实姓名不正确");
        } catch (Exception e) {
            return JsonResultS.error(e.getMessage());
        }
        // 凭据验证通过，为用户生成Bearer Token
        String token = jwtUtil.sign(loginDto.getStuNumber(), Constant.TOKEN_USER_TYPE_STUDENT);
        return JsonResultS.success(new HashMap<>() {{
            put("authorization", token);
        }});
    }

    @Override
    public JsonResultS loginBySerialNumberAndRealName(WechatStudentALLoginDto loginDto) {
        if (StringUtils.isEmpty(loginDto.getSerialNumber()) || StringUtils.isEmpty(loginDto.getRealName())) {
            return JsonResultS.error("通知书编号或真实姓名为空");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            WechatStudentUserALToken userToken = new WechatStudentUserALToken(loginDto);
            subject.login(userToken);
        } catch (UnknownAccountException e) {
            return JsonResultS.error("未知通知书编号");
        } catch (IncorrectCredentialsException e) {
            return JsonResultS.error("真实姓名不正确");
        } catch (LockedAccountException e) {
            return JsonResultS.error("账号已锁定");
        } catch (ExcessiveAttemptsException e) {
            return JsonResultS.error("通知书编号或真实姓名输入错误次数过多");
        } catch (AuthenticationException e) {
            return JsonResultS.error("通知书编号或真实姓名不正确");
        } catch (Exception e) {
            return JsonResultS.error(e.getMessage());
        }
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("serial_number", loginDto.getSerialNumber());
        wrapper.eq("mark", 1);
        Student student = studentMapper.selectOne(wrapper);
        // 凭据验证通过，为用户生成Bearer Token
        String token = jwtUtil.sign(student.getStuNumber(), Constant.TOKEN_USER_TYPE_STUDENT);
        return JsonResultS.success(new HashMap<>() {{
            put("authorization", token);
        }});
    }

    @Override
    public JsonResultS loginByTchNumberAndPassword(WechatTeacherLoginDto loginDto) {
        if (StringUtils.isEmpty(loginDto.getTchNumber()) || StringUtils.isEmpty(loginDto.getPassword())) {
            return JsonResultS.error("帐号或密码为空");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            WechatTeacherUserToken userToken = new WechatTeacherUserToken(loginDto);
            subject.login(userToken);
        } catch (UnknownAccountException e) {
            return JsonResultS.error("未知帐号");
        } catch (IncorrectCredentialsException e) {
            return JsonResultS.error("密码不正确");
        } catch (LockedAccountException e) {
            return JsonResultS.error("账号已锁定");
        } catch (ExcessiveAttemptsException e) {
            return JsonResultS.error("帐号或密码输入错误次数过多");
        } catch (AuthenticationException e) {
            return JsonResultS.error("帐号或密码不正确");
        } catch (Exception e) {
            return JsonResultS.error(e.getMessage());
        }
        String token = jwtUtil.sign(loginDto.getTchNumber(), Constant.TOKEN_USER_TYPE_TEACHER);
        return JsonResultS.success(new HashMap<>() {{
            put("authorization", token);
        }});
    }

    @Override
    public JsonResultS loginByExamNumberAndLastId(WechatStudentEXAMLoginDto dto) {
        if (StringUtils.isEmpty(dto.getRealName())
                || StringUtils.isEmpty(dto.getExamNumber())
                || StringUtils.isEmpty(dto.getLastIdNumber())) {
            return JsonResultS.error("考生号或真实姓名或身份证后六位为空");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            WechatStudentUserEXAMToken token = new WechatStudentUserEXAMToken(dto);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return JsonResultS.error("考生号+姓名对应记录不存在");
        } catch (IncorrectCredentialsException e) {
            return JsonResultS.error("考生号或身份证后六位不正确");
        } catch (LockedAccountException e) {
            return JsonResultS.error("账号已锁定");
        } catch (ExcessiveAttemptsException e) {
            return JsonResultS.error("输入错误次数过多");
        } catch (AuthenticationException e) {
            return JsonResultS.error("考生号或身份证后六位不正确");
        } catch (Exception e) {
            return JsonResultS.error(e.getMessage());
        }
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("real_name", dto.getRealName());
        wrapper.eq("exam_number", dto.getExamNumber());
        wrapper.eq("mark", 1);
        Student student = studentMapper.selectOne(wrapper);
        // 凭据验证通过，为用户生成Bearer Token
        String token = jwtUtil.sign(student.getStuNumber(), Constant.TOKEN_USER_TYPE_STUDENT);
        return JsonResultS.success(new HashMap<>() {{
            put("authorization", token);
        }});
    }

    @Override
    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional /*使用事务保证*/
    public JsonResultS bind(String code, AuthToken2CredentialDto dto) {
        WechatProgramIdentityDto wxIdentity = wxUserRegisterUtil.requestCode2Session(code);

        if (wxIdentity == null) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0242);
        }

        QueryWrapper<Wechat> wechatQueryWrapper = new QueryWrapper<>();
        wechatQueryWrapper.eq("open_id", wxIdentity.getOpenid());
        Wechat wechat = wechatMapper.selectOne(wechatQueryWrapper);
        // 如果存在源记录不会重复创建
        if (wechat == null) {
            wechat = new Wechat()
                    .setOpenId(wxIdentity.getOpenid())
                    .setSessionKey(wxIdentity.getSessionKey())
                    .setUnionId(wxIdentity.getUnionId());
            wechatMapper.insert(wechat);
        } else if (!coverBind) { // 生产环境不允许覆盖绑定
            return JsonResultS.error(ResultCodeEnum.WECHAT_USER_IS_BIND);
        }
        // TODO: 2021/9/13 待修改，绑定问题
        if (Constant.TOKEN_USER_TYPE_STUDENT.equals(dto.getType())) {
            QueryWrapper<Student> wrapper = new QueryWrapper<>();
            wrapper.eq("stu_number", dto.getNumber());
            wrapper.eq("mark", 1);
            Student student = studentMapper.selectOne(wrapper);
            student.setWxId(wechat.getId());
            studentMapper.updateById(student);
        } else if (Constant.TOKEN_USER_TYPE_TEACHER.equals(dto.getType())) {
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.eq("tch_number", dto.getNumber());
            wrapper.eq("mark", 1);
            Teacher teacher = teacherMapper.selectOne(wrapper);
            teacher.setWxId(wechat.getId());
            teacherMapper.updateById(teacher);
        } else {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0305);
        }

        return JsonResultS.success();
    }

    @Override
    public JsonResultS getBind(String code) {
        WechatProgramIdentityDto dto = wxUserRegisterUtil.requestCode2Session(code);

        if (dto == null) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0242);
        }
        QueryWrapper<Wechat> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", dto.getOpenid());
        Wechat wechat = wechatMapper.selectOne(wrapper);
        String token;
        // 微信用户未绑定情况
        if (wechat == null) {
            return JsonResultS.success(ResultCodeEnum.WECHAT_USER_NOT_BIND);
        }
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("wx_id", wechat.getId());
        Student student = studentMapper.selectOne(studentQueryWrapper);
        if (student == null) {
            QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
            teacherQueryWrapper.eq("wx_id", wechat.getId());
            Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
            if (teacher == null) {
                return JsonResultS.error(ResultCodeEnum.WECHAT_USER_NOT_BIND);
            }
            token = jwtUtil.sign(teacher.getTchNumber(), Constant.TOKEN_USER_TYPE_TEACHER);
        } else {
            token = jwtUtil.sign(student.getStuNumber(), Constant.TOKEN_USER_TYPE_STUDENT);
        }

        // 均未找到微信绑定信息
        if (token == null) {
            return JsonResultS.success(ResultCodeEnum.WECHAT_USER_NOT_BIND);
        }

        return JsonResultS.success(new HashMap<>() {{
            put("authorization", token);
        }});
    }

    /**
     * 获取用户的信息码二维码
     */
    @Override
    public JsonResultS getQRCode(AuthToken2CredentialDto dto, Integer width, Integer height) {
        try {
            if (width <= 0 || width > 1000 || height <= 0 || height > 1000) {
                return JsonResultS.error("高度或宽度值错误");
            }
        } catch (NullPointerException e) {
            return JsonResultS.error("请输入高宽");
        }
        Map<String, String> result = new HashMap<>();
        result.put("number", dto.getNumber());
        result.put("type", dto.getType());

        String json;
        // 二维码 90 秒会过期
        json = jwtUtil.sign(dto.getNumber(), dto.getType(), Constant.QRCODE_EXPIRE_TIME);
        json = json.split(" ")[1];

        String base64;
        try {
            base64 = QRCodeUtils.qrcodeImageBase64(json, height, width);
        } catch (Exception e) {
            return JsonResultS.error("创建二维码异常");
        }

        String finalBase64 = base64;
        return JsonResultS.success(new HashMap<>() {{
            put("qrcodeImg", QRCodeUtils.IMAGE_HEADER + finalBase64);
        }});
    }

    @Override
    public JsonResultS simple(String number) {
        List<UserSimpleVo> simples = wechatMapper.getUserSimpleByNumber(number);
        return JsonResultS.success(simples);
    }

}