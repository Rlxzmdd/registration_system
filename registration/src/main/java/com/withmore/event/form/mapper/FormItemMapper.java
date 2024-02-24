package com.withmore.event.form.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.event.form.constan.FormAuditNoticeStatus;
import com.withmore.event.form.entity.FormItem;
import com.withmore.event.form.vo.formItem.FormDataItemStatusVO;
import com.withmore.event.form.vo.formItem.FormDataItemVo;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 待办事项-用户提交的表单 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Mapper
@DS("registration")
public interface FormItemMapper extends BaseMapper<FormItem> {
    /**
     * 查询指定用户提交的表单数据
     *
     * @param formKey         表单模版Key
     * @param specifiedNumber 指定用户学号，工号
     * @param specifiedType   指定用户类型
     * @param nodes           权限节点
     */
    FormDataItemVo getFormDataItemByKey(@Param("formKey") String formKey,
                                        @Param("number") String specifiedNumber,
                                        @Param("type") String specifiedType,
                                        @Param("nodes") List<PermissionNode> nodes);

    /**
     * 查询用户提交的表单及其表单审核状态
     *
     * @param status 表单审核状态
     */
    List<FormDataItemStatusVO> getFormDataItemStatusList(@Param("status") FormAuditNoticeStatus status,
                                                         @Param("number") String submitNumber,
                                                         @Param("type") String submitType);

    /**
     * 撤回表单审核状态
     *
     * @param formKey      表单模版Key
     * @param submitNumber 提交人组
     */
    void retractFormItemAudit(@Param("formKey") String formKey,
                              @Param("numbers") List<String> submitNumber);
}
