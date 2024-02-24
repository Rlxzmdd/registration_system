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
import com.withmore.user.student.entity.StudentLabel;
import com.withmore.user.student.mapper.StudentLabelMapper;
import com.withmore.user.student.query.StudentLabelQuery;
import com.withmore.user.student.service.IStudentLabelService;
import com.withmore.user.student.vo.studentlabel.StudentLabelInfoVo;
import com.withmore.user.student.vo.studentlabel.StudentLabelListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
  * <p>
  * 学生标签信息表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-07-20
  */
@Service
public class StudentLabelServiceImpl extends BaseServiceImpl<StudentLabelMapper, StudentLabel> implements IStudentLabelService {

    @Autowired
    private StudentLabelMapper studentLabelMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        StudentLabelQuery studentLabelQuery = (StudentLabelQuery) query;
        // 查询条件
        QueryWrapper<StudentLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<StudentLabel> page = new Page<>(studentLabelQuery.getPage(), studentLabelQuery.getLimit());
        IPage<StudentLabel> pageData = studentLabelMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            StudentLabelListVo studentLabelListVo = Convert.convert(StudentLabelListVo.class, x);
            return studentLabelListVo;
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
        StudentLabel entity = (StudentLabel) super.getInfo(id);
        // 返回视图Vo
        StudentLabelInfoVo studentLabelInfoVo = new StudentLabelInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, studentLabelInfoVo);
        return studentLabelInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(StudentLabel entity) {
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
    public JsonResult delete(StudentLabel entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}