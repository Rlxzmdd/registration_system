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
import com.javaweb.common.exception.user.UserNotExistsException;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.PageUtil;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import com.withmore.user.permission.utils.PermissionJudge;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.mapper.StudentMapper;
import com.withmore.user.student.mapper.StudentPermissionMapper;
import com.withmore.user.student.query.StudentListQuery;
import com.withmore.user.student.query.StudentQuery;
import com.withmore.user.student.service.IStudentService;
import com.withmore.user.student.vo.student.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 学生信息表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentPermissionMapper studentPermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        StudentQuery studentQuery = (StudentQuery) query;
        // 查询条件
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Student> page = new Page<>(studentQuery.getPage(), studentQuery.getLimit());
        IPage<Student> pageData = studentMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            StudentListVo studentListVo = Convert.convert(StudentListVo.class, x);
            return studentListVo;
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
        Student entity = (Student) super.getInfo(id);
        // 返回视图Vo
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, studentInfoVo);
        return studentInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Student entity) {
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
    public JsonResult delete(Student entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }


    @Override
    public Student login(String stuNumber, String realName) {
        if (StringUtils.isEmpty(stuNumber) || StringUtils.isEmpty(realName)) {
            throw new UserNotExistsException();
        }
        // 验证密码交由Shiro 完成
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_number", stuNumber);
        wrapper.eq("mark", 1);
        Student student = studentMapper.selectOne(wrapper);

        // 用户不存在抛出异常
        if (student == null) {
            throw new UserNotExistsException();
        }
        return student;
    }

    @Override
    public Student loginBySerialNumber(String serialNumber, String realName) {
        if (StringUtils.isEmpty(serialNumber) || StringUtils.isEmpty(realName)) {
            throw new UserNotExistsException();
        }
        // 验证密码交由Shiro 完成
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("serial_number", serialNumber);
        wrapper.eq("mark", 1);
        Student student = getOne(wrapper);

        // 用户不存在抛出异常
        if (student == null) {
            throw new UserNotExistsException();
        }
        return student;
    }

    @Override
    public Student loginByExamNumber(String examNumber, String realName) {
        if (StringUtils.isEmpty(examNumber) || StringUtils.isEmpty(realName)) {
            throw new UserNotExistsException();
        }
        // 验证密码交由Shiro 完成
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_number", examNumber);
        wrapper.eq("real_name", realName);
        wrapper.eq("mark", 1);
        Student student = getOne(wrapper);
        // 用户不存在抛出异常
        if (student == null) {
            throw new UserNotExistsException();
        }
        return student;
    }

    @Override
    public JsonResultS details(AuthToken2CredentialDto dto) {
        if (StringUtils.isEmpty(dto.getNumber())) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0305);
        }
        StudentDetailsVo vo = studentMapper.getStudentDetails(dto.getNumber());
        if (vo == null) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0201);
        }
        return JsonResultS.success(vo);
    }

    @Override
    public JsonResultS details(String stuNumber, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        if (PermissionJudge.judge(stuNumber, nodes)) {
            StudentDetailsVo detailsVo = studentMapper.getStudentDetailsAuth(stuNumber, nodes);
            return JsonResultS.success(detailsVo);
        }
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0313);
    }

    @Override
    public JsonResultS simple(AuthToken2CredentialDto dto) {
        if (StringUtils.isEmpty(dto.getNumber())) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0305);
        }
        StudentSimpleVo vo = studentMapper.getStudentSimple(dto.getNumber());
        if (vo == null) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0201);
        }
        return JsonResultS.success(vo);
    }

    @Override
    public JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        List<StudentSimpleVo> vos = studentMapper.getStudentSimpleList(nodes);
        IPage<StudentSimpleVo> voIPage = PageUtil.getPages(baseQuery, vos);
        return JsonResultS.success(voIPage);
    }

    @Override
    public JsonResultS simpleAuth(String stuNumber, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        StudentSimpleVo simpleAuth = studentMapper.getStudentSimpleAuth(stuNumber, nodes);
        return JsonResultS.success(simpleAuth);
    }

    @Override
    public JsonResultS listQuery(StudentListQuery baseQuery, AuthToken2CredentialDto dto) {
        if (StringUtils.isEmpty(baseQuery.getClasses()) || StringUtils.isEmpty(baseQuery.getCollege()) || StringUtils.isEmpty(baseQuery.getMajor())) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0410);
        }
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        if (PermissionJudge.judge(baseQuery, nodes)) {
            IPage<StudentSimpleVo> list = studentMapper.getStudentSimpleListFilter(new Page<>(baseQuery.getPage(), baseQuery.getLimit()), baseQuery);
            return JsonResultS.success(list);
        }
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0313);
    }
}