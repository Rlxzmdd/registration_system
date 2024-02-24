package com.withmore.event.form.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.dto.FormSubmitStatusDto;
import com.withmore.event.form.entity.Form;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.event.form.vo.form.FormDetailVo;
import com.withmore.event.form.vo.form.FormSimpleListVo;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 待办事项-填报事项 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Mapper
@DS("registration")
public interface FormMapper extends BaseMapper<Form> {
    /**
     * 分页获取表单简略信息列表
     * 数据受权限节点限制
     *
     * @param todoFormSimpleListVoPage 分页对象
     * @param node                     权限节点
     * @return
     */
    IPage<FormSimpleListVo> getFormSimpleList(Page<FormSimpleListVo> todoFormSimpleListVoPage, @Param("node") PermissionNode node);

    /**
     * 获取表单提交状态
     *
     * @param formKeys 表单key组
     * @param dto      用户凭据
     * @return
     */
    List<FormSubmitStatusDto> getFormSubmitStatus(@Param("formKeys") List<String> formKeys, @Param("dto") AuthToken2CredentialDto dto);


    /**
     * 获取表单详情
     *
     * @param formKey 表单Key
     * @param node    权限节点
     * @return
     */
    FormDetailVo getFormDetail(@Param("formKey") String formKey, @Param("node") PermissionNode node);
}
