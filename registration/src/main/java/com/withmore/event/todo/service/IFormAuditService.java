// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.service;

import com.javaweb.common.utils.JsonResultS;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.todo.dto.FormAuditPushParamDto;
import com.withmore.event.todo.dto.FormAuditRetractParamDto;
import com.withmore.event.todo.entity.FormAudit;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 待办事项-表单审核状态表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface IFormAuditService extends IBaseService<FormAudit> {

    /**
     * 小程序用户推送表单审核结果
     *
     * @param param 请求参数
     * @param dto   用户凭据
     * @return
     */
    JsonResultS push(FormAuditPushParamDto param, AuthToken2CredentialDto dto);

    /**
     * 撤回已审核通过的表单数据
     *
     * @param param 请求参数
     * @param dto   用户凭据
     * @return
     */
    JsonResultS retract(FormAuditRetractParamDto param, AuthToken2CredentialDto dto);
}