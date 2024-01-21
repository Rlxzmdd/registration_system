// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.role.entity.WechatMiniProgramRole;
import com.withmore.user.role.query.RoleQuery;
import com.withmore.user.role.service.IWechatMiniProgramRoleService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户权限表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
@RestController
@RequestMapping("/wechat_role")
public class WechatMiniProgramRoleController extends BaseController {

    @Autowired
    private IWechatMiniProgramRoleService roleService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:role:index")
    @GetMapping("/index")
    public JsonResult index(RoleQuery query) {
        return roleService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "用户权限表", logType = LogType.INSERT)
    @RequiresPermissions("sys:role:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody WechatMiniProgramRole entity) {
        return roleService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param roleId 记录ID
     * @return
     */
    @GetMapping("/info/{roleId}")
    public JsonResult info(@PathVariable("roleId") Integer roleId) {
        return roleService.info(roleId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "用户权限表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:role:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody WechatMiniProgramRole entity) {
        return roleService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param roleIds 记录ID
     * @return
     */
    @Log(title = "用户权限表", logType = LogType.DELETE)
    @RequiresPermissions("sys:role:drop")
    @DeleteMapping("/delete/{roleIds}")
    public JsonResult delete(@PathVariable("roleIds") Integer[] roleIds) {
        return roleService.deleteByIds(roleIds);
    }


    /**
     * 获取用户具有的角色
     *
     * @param token 用户Token
     * @return
     */
    @GetMapping("/query/role")
    public JsonResultS role(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return roleService.role(dto);
    }
}