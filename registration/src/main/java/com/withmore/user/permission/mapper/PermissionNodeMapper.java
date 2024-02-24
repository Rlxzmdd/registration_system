// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.permission.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.user.permission.dto.PermissionNodeFilterDto;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限节点表-待办事项可视权限 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-27
 */
@Mapper
@DS("registration")
public interface PermissionNodeMapper extends BaseMapper<PermissionNode> {

    /**
     * 获取权限节点可访问的全部节点原子
     *
     * @param nodes 权限节点
     */
    List<PermissionNodeFilterDto> getPermissionNodeFilter(@Param("nodes") List<PermissionNode> nodes);
}
