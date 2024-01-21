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

import com.javaweb.system.common.IBaseService;
import com.javaweb.system.entity.RoleMenu;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
public interface IRoleMenuService extends IBaseService<RoleMenu> {

    /**
     * 根据角色ID删除角色菜单关系数据
     *
     * @param roleId
     */
    boolean deleteRoleMenus(Integer roleId);

}
