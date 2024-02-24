// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.wechat.dto.WechatStudentALLoginDto;
import com.withmore.user.wechat.dto.WechatStudentEXAMLoginDto;
import com.withmore.user.wechat.dto.WechatStudentLoginDto;
import com.withmore.user.wechat.dto.WechatTeacherLoginDto;
import com.withmore.user.wechat.entity.Wechat;

/**
 * <p>
 * 微信小程序用户表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-18
 */
public interface IWechatService extends IBaseService<Wechat> {

    /**
     * 小程序学生用户登录
     * 登录凭据：学号+真实姓名
     *
     * @param loginDto 登录凭据Dto
     */
    JsonResultS loginByStuNumberAndRealName(WechatStudentLoginDto loginDto);

    /**
     * 小程序学生用户登录
     * 登录凭据：通知书编号+真实姓名
     *
     * @param loginDto 登录凭据Dto
     */
    JsonResultS loginBySerialNumberAndRealName(WechatStudentALLoginDto loginDto);

    /**
     * 小程序学生用户登录
     * 登录凭据：考生号+真实姓名+身份证后六位
     *
     * @param dto 登录凭据Dto
     */
    JsonResultS loginByExamNumberAndLastId(WechatStudentEXAMLoginDto dto);

    /**
     * 绑定微信
     *
     * @param code   小程序Code
     * @param dto
     */
    JsonResultS bind(String code, AuthToken2CredentialDto dto);

    /**
     * 根据Code 获取绑定的微信信息
     *
     * @param code 小程序code
     */
    JsonResultS getBind(String code);

    /**
     * 获取信息码
     *
     * @param dto    用户信息
     * @param height 图像高度
     */
    JsonResultS getQRCode(AuthToken2CredentialDto dto, Integer width, Integer height);

    /**
     * 小程序教师用户登录
     *
     * @param loginDto 登录凭据Dto
     */
    JsonResultS loginByTchNumberAndPassword(WechatTeacherLoginDto loginDto);

    /**
     * 获取指定工号、学号极简信息
     *
     * @param number 学号，工号
     */
    JsonResultS simple(String number);


}