// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.user.role.entity.WechatMiniProgramRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.user.role.vo.role.RoleSimpleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
@Mapper
@DS("registration")
public interface WechatMiniProgramRoleMapper extends BaseMapper<WechatMiniProgramRole> {

    /**
     * 获取用户对应角色的shiro权限节点
     * 此Shiro权限节点与PermissionNode不同
     * 此权限仅适用于接口的执行权限
     *
     * @param userNumber 用户学号、工号
     * @param userType   用户类型
     * @return
     */
    List<String> getPermissionList(@Param("userNumber") String userNumber, @Param("userType") String userType);

    /**
     * 查询指定学号、工号的用户具有的角色
     *
     * @param number 学号、工号
     * @return
     */
    List<RoleSimpleVo> role(@Param("number") String number);
}
