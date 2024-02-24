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
import com.javaweb.common.exception.user.UserNotExistsException;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.user.teacher.entity.Teacher;
import com.withmore.user.teacher.mapper.TeacherMapper;
import com.withmore.user.teacher.query.TeacherQuery;
import com.withmore.user.teacher.service.ITeacherService;
import com.withmore.user.teacher.vo.teacher.TeacherInfoVo;
import com.withmore.user.teacher.vo.teacher.TeacherListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 教师信息表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        TeacherQuery teacherQuery = (TeacherQuery) query;
        // 查询条件
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Teacher> page = new Page<>(teacherQuery.getPage(), teacherQuery.getLimit());
        IPage<Teacher> pageData = teacherMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            TeacherListVo teacherListVo = Convert.convert(TeacherListVo.class, x);
            return teacherListVo;
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
        Teacher entity = (Teacher) super.getInfo(id);
        // 返回视图Vo
        TeacherInfoVo teacherInfoVo = new TeacherInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, teacherInfoVo);
        return teacherInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(Teacher entity) {
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
     */
    @Override
    public JsonResult delete(Teacher entity) {
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public Teacher login(String tchNumber, String password) {
        if (StringUtils.isEmpty(tchNumber) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        // 验证密码交由Shiro完成
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tch_number", tchNumber);
        wrapper.eq("mark", 1);
        Teacher teacher = getOne(wrapper);
        if (teacher == null) {
            throw new UserNotExistsException();
        }

        return teacher;
    }
}