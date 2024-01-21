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
import com.withmore.user.student.entity.StudentLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生标签信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Mapper
@DS("registration")
public interface StudentLabelMapper extends BaseMapper<StudentLabel> {

}
