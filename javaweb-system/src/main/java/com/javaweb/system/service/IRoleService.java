// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.IBaseService;
import com.javaweb.system.entity.Role;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 获取角色列表
     *
     */
    JsonResult getRoleList();

    /**
     * 获取角色菜单列表
     *
     * @param roleId 角色ID
     */
    JsonResult getMenuList(Integer roleId);

    /**
     * 保存角色菜单数据
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID几个
     */
    JsonResult savePermission(Integer roleId, String[] menuIds);

}
