// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.wechat.dto.WechatStudentALLoginDto;
import com.withmore.user.wechat.dto.WechatStudentEXAMLoginDto;
import com.withmore.user.wechat.dto.WechatStudentLoginDto;
import com.withmore.user.wechat.dto.WechatTeacherLoginDto;
import com.withmore.user.wechat.entity.Wechat;
import com.withmore.user.wechat.query.QRCodeQuery;
import com.withmore.user.wechat.query.WechatQuery;
import com.withmore.user.wechat.service.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 微信小程序用户表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-18
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController extends BaseController {

    @Autowired
    private IWechatService wechatService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:wechat:index")
    @GetMapping("/index")
    public JsonResult index(WechatQuery query) {
        return wechatService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "微信小程序用户表", logType = LogType.INSERT)
    @RequiresPermissions("sys:wechat:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Wechat entity) {
        return wechatService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param wechatId 记录ID
     */
    @GetMapping("/info/{wechatId}")
    public JsonResult info(@PathVariable("wechatId") Integer wechatId) {
        return wechatService.info(wechatId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "微信小程序用户表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:wechat:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Wechat entity) {
        return wechatService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param wechatIds 记录ID
     */
    @Log(title = "微信小程序用户表", logType = LogType.DELETE)
    @RequiresPermissions("sys:wechat:drop")
    @DeleteMapping("/delete/{wechatIds}")
    public JsonResult delete(@PathVariable("wechatIds") Integer[] wechatIds) {
        return wechatService.deleteByIds(wechatIds);
    }

    /**
     * 小程序学生用户登录Api
     * 登录凭据：真实姓名+学号
     *
     * @param loginDto 登录凭据
     */
    @PostMapping("/login/student")
    public JsonResultS login(WechatStudentLoginDto loginDto) {
        return wechatService.loginByStuNumberAndRealName(loginDto);
    }

    /**
     * 小程序学生用户登录Api
     * 登录凭据：录取通知书编号+真实姓名
     *
     * @param dto 登录凭据
     */
    @PostMapping("/login/studentAL")
    public JsonResultS login(WechatStudentALLoginDto dto) {
        return wechatService.loginBySerialNumberAndRealName(dto);
    }

    /**
     * 小程序学生用户登录Api
     * 登录凭据：考生编号+真实姓名+身份证后六位
     *
     * @param dto 登录凭据
     */
    @PostMapping("/login/studentEXAM")
    public JsonResultS login(WechatStudentEXAMLoginDto dto) {
        return wechatService.loginByExamNumberAndLastId(dto);
    }

    /**
     * 小程序教师用户登录Api
     *
     * @param loginDto 登录凭据
     */
    @PostMapping("/login/teacher")
    public JsonResultS login(WechatTeacherLoginDto loginDto) {
        return wechatService.loginByTchNumberAndPassword(loginDto);
    }

    /**
     * 小程序用户绑定openid
     *
     * @param code  小程序code
     * @param token 用户Token
     */
    @PostMapping("/bind")
    public JsonResultS bind(String code, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return wechatService.bind(code, dto );
    }

    /**
     * 小程序检查绑定（快捷登录）
     *
     * @param code 小程序code
     */
    @GetMapping("/get_bind/{code}")
    public JsonResultS bind(@PathVariable("code") String code) {
        return wechatService.getBind(code);
    }

    /**
     * 用户获取信息码
     *
     * @param token       用户token
     * @param qrCodeQuery 请求参数
     */
    @GetMapping("/query/qrcode")
    public JsonResultS getEliminateCode(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, QRCodeQuery qrCodeQuery) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return wechatService.getQRCode(dto, qrCodeQuery.getWidth(), qrCodeQuery.getHeight());
    }


    /**
     * 搜索指定学生、教师（工号，姓名，类型）信息
     *
     * @param number 学号、工号
     */
    @GetMapping("/find/pure/{number}")
    public JsonResultS simpleByNumber(@PathVariable String number) {
        return wechatService.simple(number);
    }

}