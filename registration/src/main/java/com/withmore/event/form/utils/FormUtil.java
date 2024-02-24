package com.withmore.event.form.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.constan.FormAvailableStatus;
import com.withmore.event.form.constan.FormCurrentStatus;
import com.withmore.event.form.dto.FormSubmitStatusDto;
import com.withmore.event.form.mapper.FormMapper;
import com.withmore.event.form.vo.form.FormSimpleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 表单工具
 * 用于设置与注入表单模版对象所处状态
 */

@Component
public class FormUtil {

    private static FormMapper formMapper;

    @Autowired
    public void setTodoFormMapper(FormMapper formMapper) {
        FormUtil.formMapper = formMapper;
    }

    /**
     * 设置表单集合的填写状态,以及通过状态
     * @param simples 表单事项列表
     * @param dto     用户凭据
     */
    public static void setFormSimpleListVoStatus(IPage<FormSimpleListVo> simples, AuthToken2CredentialDto dto) {
        List<FormSimpleListVo> records = simples.getRecords();
        List<String> formKeys = new ArrayList<>();
        for (FormSimpleListVo vo : records) {
            formKeys.add(vo.getFormKey());
        }
        List<FormSubmitStatusDto> status = formMapper.getFormSubmitStatus(formKeys, dto);
        Map<String, FormSubmitStatusDto> dtoMapper = new HashMap<>();
        for (FormSubmitStatusDto statusDto : status) {
            dtoMapper.put(statusDto.getFormKey(), statusDto);
        }
        for (FormSimpleListVo vo :
                records) {
            // 设置活动当前所处状态
            setFormStatus2Vo(vo);
            // 设置我的提交状态
            setFormStatus2Vo(vo, dtoMapper);
        }
    }

    /**
     * 设置表单状态至Vo 中
     * @param vo     VO
     * @param mapper 状态DTO映射对象
     */
    public static void setFormStatus2Vo(FormSimpleListVo vo, Map<String, FormSubmitStatusDto> mapper) {
        if (!mapper.containsKey(vo.getFormKey())) {
            vo.setSubmitStatus(FormCurrentStatus.TODO_FORM_UNDONE.getFlag());
            vo.setPassed(false);
            vo.setAuditStatus(false);
        } else {
            vo.setSubmitStatus(FormCurrentStatus.TODO_FORM_FINISH.getFlag());
            vo.setPassed(mapper.get(vo.getFormKey()).getPassed());
            vo.setAuditStatus(mapper.get(vo.getFormKey()).getAuditStatus());
        }
    }

    /**
     * 设置TodoFormSimpleListVo 当前时间段所在的状态
     */
    public static void setFormStatus2Vo(FormSimpleListVo vo) {
        FormCurrentStatus status = getFormCurrentStatus(vo);
        vo.setFormStatus(status.getFlag());
    }

    /**
     * 获取Form 当前时间段所在的状态
     * @param form 表单实体
     */
    public static <T extends FormAvailableStatus> FormCurrentStatus getFormCurrentStatus(T form) {
        Date date = new Date();
        if (date.before(form.getStartTime())) {
            // 暂未开始情况
            return FormCurrentStatus.TODO_FORM_WAIT;
        } else if (date.after(form.getStartTime()) && date.before(form.getEndTime()) || date.equals(form.getStartTime())) {
            // 正在进行时情况
            return FormCurrentStatus.TODO_FORM_PROCESS;
        } else if (date.after(form.getEndTime())) {
            // 结束填报情况
            return FormCurrentStatus.TODO_FORM_END;
        }
        return FormCurrentStatus.TODO_FORM_END;
    }
}
