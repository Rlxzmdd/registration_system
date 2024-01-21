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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.event.todo.constant.FormReviewerConstant;
import com.withmore.event.todo.entity.FormReviewer;
import com.withmore.event.todo.mapper.FormReviewerMapper;
import com.withmore.event.todo.query.FormReviewerQuery;
import com.withmore.event.todo.service.IFormReviewerService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.event.todo.vo.formreviewer.FormReviewerInfoVo;
import com.withmore.event.todo.vo.formreviewer.FormReviewerListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
  * <p>
  * 审核人映射表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-08-07
  */
@Service
public class FormReviewerServiceImpl extends BaseServiceImpl<FormReviewerMapper, FormReviewer> implements IFormReviewerService {

    @Autowired
    private FormReviewerMapper formReviewerMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        FormReviewerQuery formReviewerQuery = (FormReviewerQuery) query;
        // 查询条件
        QueryWrapper<FormReviewer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<FormReviewer> page = new Page<>(formReviewerQuery.getPage(), formReviewerQuery.getLimit());
        IPage<FormReviewer> pageData = formReviewerMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            FormReviewerListVo formReviewerListVo = Convert.convert(FormReviewerListVo.class, x);
            return formReviewerListVo;
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
        FormReviewer entity = (FormReviewer) super.getInfo(id);
        // 返回视图Vo
        FormReviewerInfoVo formReviewerInfoVo = new FormReviewerInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, formReviewerInfoVo);
        return formReviewerInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(FormReviewer entity) {
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
    public JsonResult delete(FormReviewer entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}