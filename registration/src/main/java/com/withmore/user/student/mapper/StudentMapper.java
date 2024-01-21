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
     * @return
     */
    StudentDetailsVo getStudentDetails(String stuNumber);

    /**
     * 获取指定学生详细
     *
     * @param stuNumber
     * @param nodes
     * @return
     */
    StudentDetailsVo getStudentDetailsAuth(String stuNumber, List<PermissionNode> nodes);

    /**
     * 获取学生简略信息
     *
     * @param stuNumber 学生学号
     * @return
     */
    StudentSimpleVo getStudentSimple(String stuNumber);

    /**
     * 获取指定学号的学生的简略信息
     * 在权限范围内进行查询
     *
     * @param stuNumber 学生学号
     * @param nodes     权限节点
     * @return
     */
    StudentSimpleVo getStudentSimpleAuth(@Param("stuNumber") String stuNumber, @Param("nodes") List<PermissionNode> nodes);

    /**
     * 获取指定学号的学生的原子权限
     *
     * @param stuNumber 学生学号
     * @return
     */
    StudentPermissionDto getStudentAtomPermissionNode(@Param("stuNumber") String stuNumber);

    /**
     * 分页获取学生简略信息列表
     *
     * @param nodes
     * @return
     */
//    IPage<StudentSimpleVo> getStudentSimpleList(Page<StudentSimpleVo> page, @Param("nodes") List<PermissionNode> nodes);
    List<StudentSimpleVo> getStudentSimpleList(@Param("nodes") List<PermissionNode> nodes);

    /**
     * 分页获取学生简略信息列表
     * 根据参数筛选指定范围
     * ！需提前做权限判断
     *
     * @param page  分页参数
     * @param query 筛选参数
     * @return
     */
    IPage<StudentSimpleVo> getStudentSimpleListFilter(Page<StudentSimpleVo> page, @Param("query") StudentListQuery query);



}
