// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.event.todo.dto.NoticeDetailsDto;
import com.withmore.event.todo.entity.TodoNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 待办事项-通知信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
@Mapper
public interface TodoNoticeMapper extends BaseMapper<TodoNotice> {
    List<NoticeDetailsDto> getTodoNoticeDetailsList(
            @Param("node") PermissionNode node,
            @Param("stuType") String studentTypeConstant,
            @Param("tchType") String teacherTypeConstant);
}
