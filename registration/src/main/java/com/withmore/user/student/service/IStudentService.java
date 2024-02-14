package com.withmore.user.student.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.student.entity.Student;
import com.javaweb.system.common.IBaseService;
import com.withmore.user.student.query.StudentListQuery;
import com.withmore.user.student.query.StudentSimpleQuery;

/**
 * <p>
 * 学生信息表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface IStudentService extends IBaseService<Student> {
    /**
     * 学生用户登录
     * 获取Student 实体传递给Shiro
     *
     * @param stuNumber 学号
     * @param realName  真实姓名
     * @return
     */
    Student login(String stuNumber, String realName);

    /**
     * 学生用户登录
     * 获取Student 实体传递给Shiro
     *
     * @param examNumber 考生号
     * @param realName   真实姓名
     * @return
     */
    Student loginByExamNumber(String examNumber, String realName);


    /**
     * 学生用户登录
     * 获取Student 实体传递给Shiro
     *
     * @param serialNumber 通知书编号
     * @param realName     真实姓名
     * @return
     */
    Student loginBySerialNumber(String serialNumber, String realName);

    /**
     * 获取学生详情信息
     *
     * @param dto 用户凭据
     * @return
     */
    JsonResultS details(AuthToken2CredentialDto dto);

    /**
     * 获取指定学生的详情信息
     *
     * @param stuNumber 学生学号
     * @param dto       用户凭据
     * @return
     */
    JsonResultS details(String stuNumber, AuthToken2CredentialDto dto);

    /**
     * 获取学生简略信息
     *
     * @param dto Token转化对象
     * @return
     */
    JsonResultS simple(AuthToken2CredentialDto dto);

    /**
     * 获取可访问的学生列表
     *
     * @param baseQuery 分页参数
     * @param dto       身份凭据
     * @return
     */
    JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto);

    /**
     * 获取指定学号的学生信息
     *
     * @param param 学生学号
     * @param dto       身份凭据
     * @return
     */
    JsonResultS simpleAuth(StudentSimpleQuery param, AuthToken2CredentialDto dto);

    /**
     * 筛选指定参数获取学生简略信息列表
     *
     * @param baseQuery 查询参数
     * @param dto       身份凭据
     * @return
     */
    JsonResultS listQuery(StudentListQuery baseQuery, AuthToken2CredentialDto dto);


}