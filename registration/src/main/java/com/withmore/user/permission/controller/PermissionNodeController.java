// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.permission.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.query.PermissionNodeQuery;
import com.withmore.user.permission.service.IPermissionNodeService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限节点表-待办事项可视权限 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/permission_node")
public class PermissionNodeController extends BaseController {

    @Autowired
    private IPermissionNodeService permissionNodeService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:permission_node:index")
    @GetMapping("/index")
    public JsonResult index(PermissionNodeQuery query) {
        return permissionNodeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "权限节点表-待办事项可视权限", logType = LogType.INSERT)
    @RequiresPermissions("sys:permission_node:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody PermissionNode entity) {
        return permissionNodeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param permissionnodeId 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer permissionnodeId) {
        return permissionNodeService.info(permissionnodeId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "权限节点表-待办事项可视权限", logType = LogType.UPDATE)
    @RequiresPermissions("sys:permission_node:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody PermissionNode entity) {
        return permissionNodeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "权限节点表-待办事项可视权限", logType = LogType.DELETE)
    @RequiresPermissions("sys:permission_node:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return permissionNodeService.deleteByIds(Ids);
    }

    /**
     * 获取我的权限节点列表
     *
     * @param token 用户Token
     * @return
     */
    @GetMapping("/query/list")
    public JsonResultS list(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return permissionNodeService.list(dto);
    }

    /**
     * 获取权限节点下的可访问的原子参数
     *
     * @param token 用户Token
     * @return
     */
    @GetMapping("/filter")
    public JsonResultS filter(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return permissionNodeService.filter(dto);
    }

}