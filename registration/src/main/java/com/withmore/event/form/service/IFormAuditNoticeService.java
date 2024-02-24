
package com.withmore.event.form.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.dto.FormAuditNoticeParamDto;
import com.withmore.event.form.entity.FormAuditNotice;

/**
 * <p>
 * 审核通知表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
public interface IFormAuditNoticeService extends IBaseService<FormAuditNotice> {

    /**
     * 分页查询表单审核通知
     *
     * @param param 分页参数
     * @param dto   用户凭据
     */
    JsonResultS list(FormAuditNoticeParamDto param, AuthToken2CredentialDto dto);

    /**
     * 获取指定类型的表单审核通知子列表
     *
     * @param formKey 表单类型
     * @param param   分页参数
     * @param dto     用户凭据
     */
    JsonResultS list(String formKey, FormAuditNoticeParamDto param, AuthToken2CredentialDto dto);
}