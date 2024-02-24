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

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.event.form.dto.FormAuditNoticeParamDto;
import com.withmore.event.form.entity.FormAuditNotice;
import com.withmore.event.form.query.FormAuditNoticeQuery;
import com.withmore.event.form.service.IFormAuditNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审核通知表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/form_audit_notice")
public class FormAuditNoticeController extends BaseController {

    @Autowired
    private IFormAuditNoticeService formAuditNoticeService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:form_audit_notice:index")
    @GetMapping("/index")
    public JsonResult index(FormAuditNoticeQuery query) {
        return formAuditNoticeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "审核通知表", logType = LogType.INSERT)
    @RequiresPermissions("sys:form_audit_notice:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody FormAuditNotice entity) {
        return formAuditNoticeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return formAuditNoticeService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "审核通知表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:form_audit_notice:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody FormAuditNotice entity) {
        return formAuditNoticeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     */
    @Log(title = "审核通知表", logType = LogType.DELETE)
    @RequiresPermissions("sys:form_audit_notice:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return formAuditNoticeService.deleteByIds(Ids);
    }

    /**
     * 查询表单审核通知信息
     *
     * @param token 用户Token
     */
    @GetMapping("/query/list")
    public JsonResultS list(FormAuditNoticeParamDto param, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formAuditNoticeService.list(param, dto);
    }

    /**
     * 分页查询表单审核通知指定表单类型的通知列表
     *
     * @param formKey 表单Key
     * @param token   用户Token
     */
    @GetMapping("/query/subList/{formKey}")
    public JsonResultS list(@PathVariable String formKey,
                            FormAuditNoticeParamDto param,
                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formAuditNoticeService.list(formKey, param, dto);
    }

}