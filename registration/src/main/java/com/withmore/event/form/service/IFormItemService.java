
package com.withmore.event.form.service;

import com.javaweb.common.utils.JsonResultS;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.dto.FormPushParamDto;
import com.withmore.event.form.dto.FormSpecifiedUserDto;
import com.withmore.event.form.entity.FormItem;
import com.javaweb.system.common.IBaseService;
import com.withmore.event.form.vo.formItem.FormItemStatusDto;

/**
 * <p>
 * 待办事项-用户提交的表单 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface IFormItemService extends IBaseService<FormItem> {

    /**
     * 获取指定FormKey 用户已提交的表单数据
     *
     * @param formKey 表单Key
     * @param dto     用户凭据
     * @return
     */
    JsonResultS queryForm(String formKey, AuthToken2CredentialDto dto);

    /**
     * 查询其他用户提交的表单数据
     *
     * @param formKey 表单Key
     * @param param   查询参数
     * @param dto     用户凭据
     * @return
     */
    JsonResultS specified(String formKey, FormSpecifiedUserDto param, AuthToken2CredentialDto dto);

    /**
     * 用户提交表单数据
     *
     * @param formKey  表单Key
     * @param formData 表单数据
     * @param dto      用户凭据
     * @return
     */
    JsonResultS push(String formKey, FormPushParamDto formData, AuthToken2CredentialDto dto);

    /**
     * 用户提交的表单及其审核状态
     *
     * @param param 查询参数
     * @param dto   用户凭据
     * @return
     */
    JsonResultS statusList(FormItemStatusDto param, AuthToken2CredentialDto dto);
}