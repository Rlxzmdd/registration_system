
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

import com.withmore.event.todo.dto.FormCreateParamDto;
import com.withmore.event.todo.entity.Form;
import com.withmore.event.todo.entity.FormPermission;
import com.withmore.event.todo.mapper.FormItemMapper;
import com.withmore.event.todo.mapper.FormMapper;
import com.withmore.event.todo.mapper.FormPermissionMapper;
import com.withmore.event.todo.query.TodoFormQuery;
import com.withmore.event.todo.service.IFormService;
import com.withmore.event.todo.utils.FormUtil;
import com.withmore.event.todo.vo.form.*;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 待办事项-填报事项 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class FormServiceImpl extends BaseServiceImpl<FormMapper, Form> implements IFormService {

    @Autowired
    private FormMapper formMapper;

    @Autowired
    private FormPermissionMapper formPermissionMapper;

    @Autowired
    private FormItemMapper formItemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        TodoFormQuery todoFormQuery = (TodoFormQuery) query;
        // 查询条件
        QueryWrapper<Form> queryWrapper = new QueryWrapper<>();
        // 事项标题
        if (!StringUtils.isEmpty(todoFormQuery.getTitle())) {
            queryWrapper.like("title", todoFormQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Form> page = new Page<>(todoFormQuery.getPage(), todoFormQuery.getLimit());
        IPage<Form> pageData = formMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            FormListVo formListVo = Convert.convert(FormListVo.class, x);
            return formListVo;
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
        Form entity = (Form) super.getInfo(id);
        // 返回视图Vo
        FormInfoVo formInfoVo = new FormInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, formInfoVo);
        return formInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Form entity) {
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
    public JsonResult delete(Form entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public JsonResultS push(FormCreateParamDto param, AuthToken2CredentialDto dto) {
        Form form = new Form()
                .setTitle(param.getTitle())
                .setTemplate(param.getTemplate())
                .setInitiatorNumber(dto.getNumber())
                .setInitiatorType(dto.getType())
                .setFormKey(CommonUtils.getRandomStr(false, 11))
                .setReviewerId(param.getReviewerId())
                .setAudit(param.getAudit())
                .setStartTime(param.getStartTime())
                .setEndTime(param.getEndTime());

        formMapper.insert(form);
        for (Integer id : param.getPermissions()) {
            FormPermission permission = new FormPermission()
                    .setPermissionId(id)
                    .setFormId(form.getId());
            formPermissionMapper.insert(permission);
        }
        return JsonResultS.success(FormSimpleVo.builder().formKey(form.getFormKey()).id(form.getId()).build());
    }


    @Override
    public JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto) {
        PermissionNode node = PermissionConvert.convert2Node(dto);
        IPage<FormSimpleListVo> simples = formMapper.getFormSimpleList(new Page<>(baseQuery.getPage(), baseQuery.getLimit()), node);
        // 设置关于表单状态的动态属性
        FormUtil.setFormSimpleListVoStatus(simples, dto);
        return JsonResultS.success(simples);
    }

    @Override
    public JsonResultS detail(String formKey, AuthToken2CredentialDto dto) {
        PermissionNode node = PermissionConvert.convert2Node(dto);
        FormDetailVo vo = formMapper.getFormDetail(formKey, node);
        return JsonResultS.success(vo);
    }
}