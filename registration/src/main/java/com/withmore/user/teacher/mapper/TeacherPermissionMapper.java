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
import com.withmore.user.teacher.dto.TeacherPermissionDto;
import com.withmore.user.teacher.entity.TeacherPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 教师可访问权限表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-08-01
 */
@Mapper
@DS("registration")
public interface TeacherPermissionMapper extends BaseMapper<TeacherPermission> {

    List<TeacherPermissionDto> getTeacherViewPermission(@Param("tchNumber") String tchNumber);
}
