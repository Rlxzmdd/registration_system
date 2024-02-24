// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.event.form.dto.FormAuditPushParamDto;
import com.withmore.event.form.dto.FormAuditRetractParamDto;
import com.withmore.event.form.entity.FormAudit;
import com.withmore.event.form.query.FormAuditQuery;
import com.withmore.event.form.service.IFormAuditService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 待办事项-表单审核状态表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/form_audit")
public class FormAuditController extends BaseController {

    @Autowired
    private IFormAuditService formAuditService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:form_audit:index")
    @GetMapping("/index")
    public JsonResult index(FormAuditQuery query) {
        return formAuditService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-表单审核状态表", logType = LogType.INSERT)
    @RequiresPermissions("sys:form_audit:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody FormAudit entity) {
        return formAuditService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return formAuditService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-表单审核状态表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:form_audit:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody FormAudit entity) {
        return formAuditService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "待办事项-表单审核状态表", logType = LogType.DELETE)
    @RequiresPermissions("sys:form_audit:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return formAuditService.deleteByIds(Ids);
    }

    /**
     * 小程序用户提交表单审核结果
     *
     * @param param 请求参数
     * @param token 用户Token
     * @return
     */
    @PostMapping("/push")
    public JsonResultS push(FormAuditPushParamDto param, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formAuditService.push(param, dto);
    }

    /**
     * 撤回已通过的表单
     *
     * @param param 表单参数
     * @param token 用户Token
     * @return
     */
    @PostMapping("/retract")
    public JsonResultS retract(FormAuditRetractParamDto param, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formAuditService.retract(param, dto);
    }

}