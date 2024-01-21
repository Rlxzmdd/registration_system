// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.teacher.dto.TeacherPermissionDto;
import com.withmore.user.teacher.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 教师信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    TeacherPermissionDto getTeacherAtomPermissionNode(@Param( "number") String number);
}
