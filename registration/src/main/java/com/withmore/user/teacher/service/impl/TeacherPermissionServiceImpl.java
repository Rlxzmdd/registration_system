// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.user.teacher.constant.TeacherPermissionConstant;
import com.withmore.user.teacher.entity.TeacherPermission;
import com.withmore.user.teacher.mapper.TeacherPermissionMapper;
import com.withmore.user.teacher.query.TeacherPermissionQuery;
import com.withmore.user.teacher.service.ITeacherPermissionService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.user.teacher.vo.teacherpermission.TeacherPermissionInfoVo;
import com.withmore.user.teacher.vo.teacherpermission.TeacherPermissionListVo;
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
  * 教师可访问权限表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-08-01
  */
@Service
public class TeacherPermissionServiceImpl extends BaseServiceImpl<TeacherPermissionMapper, TeacherPermission> implements ITeacherPermissionService {

    @Autowired
    private TeacherPermissionMapper teacherPermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        TeacherPermissionQuery teacherPermissionQuery = (TeacherPermissionQuery) query;
        // 查询条件
        QueryWrapper<TeacherPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<TeacherPermission> page = new Page<>(teacherPermissionQuery.getPage(), teacherPermissionQuery.getLimit());
        IPage<TeacherPermission> pageData = teacherPermissionMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            TeacherPermissionListVo teacherPermissionListVo = Convert.convert(TeacherPermissionListVo.class, x);
            return teacherPermissionListVo;
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
        TeacherPermission entity = (TeacherPermission) super.getInfo(id);
        // 返回视图Vo
        TeacherPermissionInfoVo teacherPermissionInfoVo = new TeacherPermissionInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, teacherPermissionInfoVo);
        return teacherPermissionInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(TeacherPermission entity) {
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
    public JsonResult delete(TeacherPermission entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}