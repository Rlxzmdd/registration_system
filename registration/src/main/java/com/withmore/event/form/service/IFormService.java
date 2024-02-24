// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.dto.FormCreateParamDto;
import com.withmore.event.form.entity.Form;

/**
 * <p>
 * 待办事项-填报事项 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface IFormService extends IBaseService<Form> {

    /**
     * 创建表单模版
     *
     * @param param 表单模版参数
     * @param dto   用户凭据
     */
    JsonResultS push(FormCreateParamDto param, AuthToken2CredentialDto dto);


    /**
     * 查询表单简略列表
     *
     * @param baseQuery 分页参数
     * @param dto       用户凭据
     */
    JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto);

    /**
     * 获取表单详情
     *
     * @param formKey 表单Key
     * @param dto     用户凭据
     */
    JsonResultS detail(String formKey, AuthToken2CredentialDto dto);


}