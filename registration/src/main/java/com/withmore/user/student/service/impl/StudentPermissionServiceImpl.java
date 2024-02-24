// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.user.student.entity.StudentPermission;
import com.withmore.user.student.mapper.StudentPermissionMapper;
import com.withmore.user.student.query.StudentPermissionQuery;
import com.withmore.user.student.service.IStudentPermissionService;
import com.withmore.user.student.vo.studentpermission.StudentPermissionInfoVo;
import com.withmore.user.student.vo.studentpermission.StudentPermissionListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
  * <p>
  * 学生-学生可访问权限表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-07-29
  */
@Service
public class StudentPermissionServiceImpl extends BaseServiceImpl<StudentPermissionMapper, StudentPermission> implements IStudentPermissionService {

    @Autowired
    private StudentPermissionMapper studentPermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        StudentPermissionQuery studentPermissionQuery = (StudentPermissionQuery) query;
        // 查询条件
        QueryWrapper<StudentPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<StudentPermission> page = new Page<>(studentPermissionQuery.getPage(), studentPermissionQuery.getLimit());
        IPage<StudentPermission> pageData = studentPermissionMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            StudentPermissionListVo studentPermissionListVo = Convert.convert(StudentPermissionListVo.class, x);
            return studentPermissionListVo;
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
        StudentPermission entity = (StudentPermission) super.getInfo(id);
        // 返回视图Vo
        StudentPermissionInfoVo studentPermissionInfoVo = new StudentPermissionInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, studentPermissionInfoVo);
        return studentPermissionInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(StudentPermission entity) {
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
    public JsonResult delete(StudentPermission entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}