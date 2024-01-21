// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.PageUtil;
import com.withmore.event.todo.constant.FormCurrentStatus;
import com.withmore.event.todo.dto.FormPushParamDto;
import com.withmore.event.todo.dto.FormSpecifiedUserDto;
import com.withmore.event.todo.entity.*;
import com.withmore.event.todo.mapper.*;
import com.withmore.event.todo.query.FormItemQuery;
import com.withmore.event.todo.service.IFormItemService;
import com.withmore.event.todo.utils.FormAuditUtil;
import com.withmore.event.todo.utils.FormUtil;
import com.withmore.event.todo.vo.form.FormSubmitVo;
import com.withmore.event.todo.vo.formItem.*;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 待办事项-用户提交的表单 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class FormItemServiceImpl extends BaseServiceImpl<FormItemMapper, FormItem> implements IFormItemService {

    @Autowired
    private FormItemMapper formItemMapper;

    @Autowired
    private FormAuditNoticeMapper formAuditNoticeMapper;

    @Autowired
    private FormMapper formMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        FormItemQuery formItemQuery = (FormItemQuery) query;
        // 查询条件
        QueryWrapper<FormItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<FormItem> page = new Page<>(formItemQuery.getPage(), formItemQuery.getLimit());
        IPage<FormItem> pageData = formItemMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            FormItemListVo formItemListVo = Convert.convert(FormItemListVo.class, x);
            return formItemListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        FormItem entity = (FormItem) super.getInfo(id);
        // 返回视图Vo
        FormItemInfoVo formItemInfoVo = new FormItemInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, formItemInfoVo);
        return formItemInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(FormItem entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
            entity.setUpdateUser(1);
        } else {
            entity.setCreateTime(DateUtils.now());
            entity.setCreateUser(1);
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(FormItem entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }


    @Override
    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public JsonResultS push(String formKey, FormPushParamDto formData, AuthToken2CredentialDto dto) {
        QueryWrapper<Form> formQueryWrapper = new QueryWrapper<>();
        formQueryWrapper.eq("form_key", formKey);
        formQueryWrapper.eq("mark", 1);
        Form form = formMapper.selectOne(formQueryWrapper);
        if (form == null) {
            return JsonResultS.error(ResultCodeEnum.FORM_TEMPLATE_NOT_EXISTS);
        }
        // 表单不在填写时间段判断
        FormCurrentStatus formStatus = FormUtil.getFormCurrentStatus(form);
        if (formStatus != FormCurrentStatus.TODO_FORM_PROCESS) {
            return JsonResultS.error(ResultCodeEnum.FORM_IS_NOT_IN_PROCESS);
        }
        // 检查是否已提交 , 取时间倒序的最后一条数据
        QueryWrapper<FormItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("submit_number", dto.getNumber());
        queryWrapper.eq("user_type", dto.getType());
        queryWrapper.eq("mark", 1);
        queryWrapper.eq("form_key", formKey);
        queryWrapper.orderByDesc("update_time");
        queryWrapper.last("LIMIT 1");
        FormItem item = formItemMapper.selectOne(queryWrapper);
        // 用户首次提交表单情况
        if (item == null) {
            item = new FormItem();
            item.setSubmitNumber(dto.getNumber());
            item.setUserType(dto.getType());
            item.setFormKey(formKey);
            item.setItemUuid(UUID.randomUUID().toString());
            item.setContent(formData.getData());
            if (form.getAudit()) {
                // 如果需要审核则创建审核通知
                FormAuditUtil.pushFormAuditNotice(form, item, false);
            } else {
                // 无需审核信息直接通过
                item.setAuditPassed(true);
                item.setAuditFinish(true);
            }
            formItemMapper.insert(item);

        } else if (!formData.getOverride()) {
            // 记录存在又不可覆盖情况
            return JsonResultS.error(
                    FormSubmitVo.builder()
                            .formUUID(item.getItemUuid())
                            .auditPassed(item.getAuditPassed())
                            .build(),
                    ResultCodeEnum.FORM_DATA_EXISTS);
        } else if (formData.getOverride()) {
            // TODO: 2021/8/27 获取最后一个审核记录
            FormAuditNotice lastNotice = formAuditNoticeMapper.getLastFormAuditNotice(item.getItemUuid());
            // 更新情况 override 需要为true
            item.setContent(formData.getData());
            item.setUpdateTime(new Date());
            // 表单存在，并且需要审核情况
            if (form.getAudit()) {
                /*
                 * 存在已上传的表单数据，并需要审核
                 * 1. 用户审核被拒绝重新提交
                 * 2. 用户已通过的数据有误，重新提交发给最后的审核人
                 */
                // 已通过并审核完结并要覆盖的情况
                if (item.getAuditPassed() && item.getAuditFinish()) {
                    /*
                    已通过的并要覆盖的情况，只会通知该审核流的最后一位审核人
                     */
                    FormAuditUtil.pushFormAuditNotice(form, item, false);
                    item.setAuditFinish(false);
                    item.setAuditPassed(false);
                } else if ((lastNotice.getAuditId() == null && item.getAuditStep() != 1)
                ) {
                     /*
                     不允许覆盖条件
                     审核未完结，未通过，并且非"第一审核"人未进行审核
                      */
                    return JsonResultS.error(ResultCodeEnum.FORM_AUDIT_IS_NOT_FINISH);
                } else if (!item.getAuditPassed() && !item.getAuditFinish() && lastNotice.getAuditId() != null) {
                    /*
                    审核未通过，并且未完结的覆盖情况
                    需要重新发起审核
                    注：此时如最后一条审核通知为NULL，不发起通知，否则将会出现重复通知
                     */
                    FormAuditUtil.pushFormAuditNotice(form, item, false);
                }
            } else {
                // 无需审核信息直接通过
                item.setAuditPassed(true);
                item.setAuditFinish(true);
            }
            formItemMapper.updateById(item);
        } else {
            // 缺少请求参数
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0410);
        }

        return JsonResultS.success(
                FormSubmitVo.builder()
                        .formUUID(item.getItemUuid())
                        .auditPassed(item.getAuditPassed())
                        .build()
        );
    }

    @Override
    public JsonResultS queryForm(String formKey, AuthToken2CredentialDto dto) {
        QueryWrapper<FormItem> wrapper = new QueryWrapper<>();
        wrapper.eq("form_key", formKey);
        wrapper.eq("submit_number", dto.getNumber());
        wrapper.eq("user_type", dto.getType());
        wrapper.eq("mark", 1);
        FormItem item = formItemMapper.selectOne(wrapper);
        if (item == null) {
            // 表单从未提交情况
            return JsonResultS.error(ResultCodeEnum.FORM_DATA_IS_NOT_SUBMIT);
        }
        return JsonResultS.success(item.getContent());
    }

    @Override
    public JsonResultS specified(String formKey, FormSpecifiedUserDto param, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        FormDataItemVo itemVo = formItemMapper.getFormDataItemByKey(formKey, param.getSpecifiedNumber(), param.getSpecifiedType(), nodes);
        return JsonResultS.success(itemVo);
    }

    @Override
    public JsonResultS statusList(FormItemStatusDto param, AuthToken2CredentialDto dto) {
        List<FormDataItemStatusVO> voList = formItemMapper.getFormDataItemStatusList(
                param.getStatus(),
                dto.getNumber(),
                dto.getType());
        IPage<FormDataItemStatusVO> pages = PageUtil.getPages(param, voList);
        return JsonResultS.success(pages);
    }
}