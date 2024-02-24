
package com.withmore.event.form.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.form.dto.FormAuditNoticeTranDto;
import com.withmore.event.form.dto.FormAuditPushParamDto;
import com.withmore.event.form.dto.FormAuditRetractParamDto;
import com.withmore.event.form.entity.Form;
import com.withmore.event.form.entity.FormAudit;
import com.withmore.event.form.entity.FormItem;
import com.withmore.event.form.mapper.FormAuditMapper;
import com.withmore.event.form.mapper.FormAuditNoticeMapper;
import com.withmore.event.form.mapper.FormItemMapper;
import com.withmore.event.form.mapper.FormMapper;
import com.withmore.event.form.query.FormAuditQuery;
import com.withmore.event.form.service.IFormAuditService;
import com.withmore.event.form.utils.FormAuditUtil;
import com.withmore.event.form.vo.formAudit.FormAuditInfoVo;
import com.withmore.event.form.vo.formAudit.FormAuditListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 待办事项-表单审核状态表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class FormAuditServiceImpl extends BaseServiceImpl<FormAuditMapper, FormAudit> implements IFormAuditService {

    @Autowired
    private FormAuditMapper formAuditMapper;

    @Autowired
    private FormAuditNoticeMapper formAuditNoticeMapper;

    @Autowired
    private FormItemMapper formItemMapper;

    @Autowired
    private FormMapper formMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        FormAuditQuery formAuditQuery = (FormAuditQuery) query;
        // 查询条件
        QueryWrapper<FormAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<FormAudit> page = new Page<>(formAuditQuery.getPage(), formAuditQuery.getLimit());
        IPage<FormAudit> pageData = formAuditMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            FormAuditListVo formAuditListVo = Convert.convert(FormAuditListVo.class, x);
            return formAuditListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     */
    @Override
    public Object getInfo(Serializable id) {
        FormAudit entity = (FormAudit) super.getInfo(id);
        // 返回视图Vo
        FormAuditInfoVo formAuditInfoVo = new FormAuditInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, formAuditInfoVo);
        return formAuditInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(FormAudit entity) {
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
     */
    @Override
    public JsonResult delete(FormAudit entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public JsonResultS push(FormAuditPushParamDto param, AuthToken2CredentialDto dto) {
        // 获取与之对应的审核通知记录实体
        FormAuditNoticeTranDto notice = formAuditNoticeMapper.getAuditNoticeByFormKeyAndUuid(param.getFormKey(), param.getUuid(), dto.getNumber(), dto.getType());
        if (notice == null) {
            // 不存在可能越权，或者已被审核完成
            return JsonResultS.error(ResultCodeEnum.FORM_AUDIT_IS_NOT_EXISTS);
        }
        // 已审核并通过的情况
        if (notice.getIsThrough() != null && notice.getIsThrough() || notice.getAuditId() != null) {
            return JsonResultS.error(ResultCodeEnum.FORM_AUDIT_ALREADY);
        }
        // 获取用户提交的表单数据对象
        QueryWrapper<FormItem> itemWrapper = new QueryWrapper<>();
        itemWrapper.eq("form_key", param.getFormKey());
        itemWrapper.eq("item_uuid", param.getUuid());
        itemWrapper.eq("mark", 1);
        FormItem item = formItemMapper.selectOne(itemWrapper);
        if (item == null) {
            return JsonResultS.error(ResultCodeEnum.FORM_DATA_IS_NOT_SUBMIT);
        }

        // 创建审核结果记录
        FormAudit formAudit = new FormAudit();
        formAudit.setFormKey(param.getFormKey());
        formAudit.setItemUuid(param.getUuid());
        formAudit.setOpinion(param.getOpinion());
        formAudit.setIsThrough(param.getThrough());
        formAuditMapper.insert(formAudit);

        // 更新审核结果ID 记录到通知记录中
        notice.setAuditId(formAudit.getId());
        formAuditNoticeMapper.updateById(notice);
        // 判断是否通过，审核不通过则不对下一位审核人发送通知
        if (!param.getThrough()) {
            return JsonResultS.success();
        }
        // 若是通过，则通知下一位，或者为item 设置审核完结状态
        QueryWrapper<Form> formQueryWrapper = new QueryWrapper<>();
        formQueryWrapper.eq("form_key", formAudit.getFormKey());
        formQueryWrapper.eq("mark", 1);
        Form form = formMapper.selectOne(formQueryWrapper);
        FormAuditUtil.pushFormAuditNotice(form, item, true);
        return JsonResultS.success();
    }

    @Override
    public JsonResultS retract(FormAuditRetractParamDto param, AuthToken2CredentialDto dto) {

        formItemMapper.retractFormItemAudit(param.getFormKey(), param.getSubmitNumber());
        return JsonResultS.success();
    }
}