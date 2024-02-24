// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.system.common.BaseQuery;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.student.dto.StudentPermissionDto;
import com.withmore.user.student.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.user.student.query.StudentListQuery;
import com.withmore.user.student.query.StudentSimpleQuery;
import com.withmore.user.student.vo.student.StudentDetailsVo;
import com.withmore.user.student.vo.student.StudentSimpleVo;
import com.withmore.user.student.vo.student.UserSimpleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学生信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 获取学生详情信息
     *
     * @param stuNumber 学生学号
     */
    StudentDetailsVo getStudentDetails(String stuNumber);

    /**
     * 获取指定学生详细
     *
     * @param stuNumber
     * @param nodes
     */
    StudentDetailsVo getStudentDetailsAuth(String stuNumber, List<PermissionNode> nodes);

    /**
     * 根据鉴权身份信息匹配相关学生
     *
     * @param fuzzyName    模糊名称
     * @param idCardPrefix 身份证前缀
     * @param idCardSuffix 身份证后缀
     * @param gradePrefix  所属年级匹配
     */
    Student getStudentIdentity(String fuzzyName, String idCardPrefix, String idCardSuffix, String gradePrefix);

    /**
     * 获取学生简略信息
     *
     * @param stuNumber 学生学号
     */
    StudentSimpleVo getStudentSimple(String stuNumber);

    /**
     * 获取指定学号的学生的简略信息
     * 在权限范围内进行查询
     *
     * @param param 学生学号
     * @param nodes 权限节点
     */
    IPage<StudentSimpleVo> getStudentSimpleAuth(Page<StudentSimpleVo> page, @Param("param") StudentSimpleQuery param, @Param("nodes") List<PermissionNode> nodes);

    /**
     * 获取指定学号的学生的原子权限
     *
     * @param stuNumber 学生学号
     */
    StudentPermissionDto getStudentAtomPermissionNode(@Param("stuNumber") String stuNumber);

    /**
     * 分页获取学生简略信息列表
     *
     * @param nodes
     */
    List<StudentSimpleVo> getStudentSimpleList(@Param("nodes") List<PermissionNode> nodes);

    /**
     * 分页获取学生简略信息列表
     * 根据参数筛选指定范围
     * ！需提前做权限判断
     *
     * @param page  分页参数
     * @param query 筛选参数
     */
    IPage<StudentSimpleVo> getStudentSimpleListFilter(Page<StudentSimpleVo> page, @Param("query") StudentListQuery query);


}
