// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.constant.ExampleConstant;
import com.javaweb.system.entity.Example;
import com.javaweb.system.mapper.ExampleMapper;
import com.javaweb.system.query.ExampleQuery;
import com.javaweb.system.service.IExampleService;
import com.javaweb.system.utils.ShiroUtils;
import com.javaweb.system.vo.example.ExampleInfoVo;
import com.javaweb.system.vo.example.ExampleListVo;
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
  * 演示案例表 服务类实现
  * </p>
  *
  * @author 鲲鹏
  * @since 2021-03-27
  */
@Service
public class ExampleServiceImpl extends BaseServiceImpl<ExampleMapper, Example> implements IExampleService {

    @Autowired
    private ExampleMapper exampleMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ExampleQuery exampleQuery = (ExampleQuery) query;
        // 查询条件
        QueryWrapper<Example> queryWrapper = new QueryWrapper<>();
        // 案例名称
        if (!StringUtils.isEmpty(exampleQuery.getName())) {
            queryWrapper.like("name", exampleQuery.getName());
        }
        // 状态：1正常 2停用
        if (!StringUtils.isEmpty(exampleQuery.getStatus())) {
            queryWrapper.eq("status", exampleQuery.getStatus());
        }
        // 类型：1京东 2淘宝 3拼多多 4唯品会
        if (!StringUtils.isEmpty(exampleQuery.getType())) {
            queryWrapper.eq("type", exampleQuery.getType());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Example> page = new Page<>(exampleQuery.getPage(), exampleQuery.getLimit());
        IPage<Example> pageData = exampleMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ExampleListVo exampleListVo = Convert.convert(ExampleListVo.class, x);
            // 头像地址
            if (!StringUtils.isEmpty(exampleListVo.getAvatar())) {
                exampleListVo.setAvatar(CommonUtils.getImageURL(exampleListVo.getAvatar()));
            }
            // 状态描述
            if (exampleListVo.getStatus() != null && exampleListVo.getStatus() > 0) {
                exampleListVo.setStatusName(ExampleConstant.EXAMPLE_STATUS_LIST.get(exampleListVo.getStatus()));
            }
            // 类型描述
            if (exampleListVo.getType() != null && exampleListVo.getType() > 0) {
                exampleListVo.setTypeName(ExampleConstant.EXAMPLE_TYPE_LIST.get(exampleListVo.getType()));
            }
            return exampleListVo;
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
        Example entity = (Example) super.getInfo(id);
        // 返回视图Vo
        ExampleInfoVo exampleInfoVo = new ExampleInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, exampleInfoVo);
        // 头像
        if (!StringUtils.isEmpty(exampleInfoVo.getAvatar())) {
            exampleInfoVo.setAvatar(CommonUtils.getImageURL(exampleInfoVo.getAvatar()));
        }
        return exampleInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Example entity) {
        // 头像
        if (entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
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
    public JsonResult delete(Example entity) {
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(Example entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

}