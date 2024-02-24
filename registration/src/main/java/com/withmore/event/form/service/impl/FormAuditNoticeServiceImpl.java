// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.PageUtil;
import com.withmore.event.form.constan.FormAuditNoticeStatus;
import com.withmore.event.form.dto.FormAuditNoticeParamDto;
import com.withmore.event.form.entity.FormAuditNotice;
import com.withmore.event.form.mapper.FormAuditNoticeMapper;
import com.withmore.event.form.query.FormAuditNoticeQuery;
import com.withmore.event.form.service.IFormAuditNoticeService;
import com.withmore.event.form.vo.formAuditNotice.FormAuditNoticeInfoVo;
import com.withmore.event.form.vo.formAuditNotice.FormAuditNoticeListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.withmore.event.form.vo.formAuditNotice.FormAuditNoticeSimpleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 审核通知表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@Service
public class FormAuditNoticeServiceImpl extends BaseServiceImpl<FormAuditNoticeMapper, FormAuditNotice> implements IFormAuditNoticeService {

    @Autowired
    private FormAuditNoticeMapper formAuditNoticeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        FormAuditNoticeQuery formAuditNoticeQuery = (FormAuditNoticeQuery) query;
        // 查询条件
        QueryWrapper<FormAuditNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<FormAuditNotice> page = new Page<>(formAuditNoticeQuery.getPage(), formAuditNoticeQuery.getLimit());
        IPage<FormAuditNotice> pageData = formAuditNoticeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            FormAuditNoticeListVo formAuditNoticeListVo = Convert.convert(FormAuditNoticeListVo.class, x);
            return formAuditNoticeListVo;
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
        FormAuditNotice entity = (FormAuditNotice) super.getInfo(id);
        // 返回视图Vo
        FormAuditNoticeInfoVo formAuditNoticeInfoVo = new FormAuditNoticeInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, formAuditNoticeInfoVo);
        return formAuditNoticeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(FormAuditNotice entity) {
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
    public JsonResult delete(FormAuditNotice entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 设置审核通知列表信息状态
     *
     * @param auditNotice 审核通知item
     */
    private void setStatus2FormAuditNoticeSimpleVo(FormAuditNoticeSimpleVo auditNotice) {
        if (!auditNotice.getAuditStatus()) {
            auditNotice.setStatus(FormAuditNoticeStatus.FORM_AUDIT_WAIT.getStatus());
        } else if (auditNotice.getAuditStatus() && auditNotice.getPassed()) {
            auditNotice.setStatus(FormAuditNoticeStatus.FORM_AUDIT_PASSED.getStatus());
        } else if (auditNotice.getAuditStatus() && !auditNotice.getPassed()) {
            auditNotice.setStatus(FormAuditNoticeStatus.FORM_AUDIT_REJECT.getStatus());
        }
    }


    @Override
    public JsonResultS list(FormAuditNoticeParamDto param, AuthToken2CredentialDto dto) {
        List<FormAuditNoticeSimpleVo> auditNotices = formAuditNoticeMapper.getAuditNotices(param.getSubLimit(),
                param.getStatus(),
                dto.getNumber(),
                dto.getType());
        auditNotices.forEach(this::setStatus2FormAuditNoticeSimpleVo);
        Map<String, List<FormAuditNoticeSimpleVo>> mapper = new HashMap<>();
        for (FormAuditNoticeSimpleVo vo :
                auditNotices) {
            if (!mapper.containsKey(vo.getTitle())) {
                mapper.put(vo.getTitle(), new ArrayList<>());
            }
            mapper.get(vo.getTitle()).add(vo);
        }
        List<List<FormAuditNoticeSimpleVo>> values = new ArrayList<>(mapper.values());
        IPage<List<FormAuditNoticeSimpleVo>> pages = PageUtil.getPages(param, values);
        return JsonResultS.success(pages);
    }

    @Override
    public JsonResultS list(String formKey, FormAuditNoticeParamDto param, AuthToken2CredentialDto dto) {
        IPage<FormAuditNoticeSimpleVo> auditNotices = formAuditNoticeMapper.getAuditNoticeByFormKey(new Page<>(param.getPage(), param.getLimit()), formKey,
                param.getStatus(),
                dto.getNumber(),
                dto.getType());
        auditNotices.getRecords().forEach(this::setStatus2FormAuditNoticeSimpleVo);
        return JsonResultS.success(auditNotices);
    }
}