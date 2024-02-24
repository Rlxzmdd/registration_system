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
import com.javaweb.system.entity.Menu;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 获取导航菜单
     *
     * @param userId 用户ID
     */
    List<Menu> getMenuList(Integer userId);

    /**
     * 获取所有菜单列表
     *
     */
    List<Menu> getMenuAll();

    /**
     * 获取节点权限
     *
     * @param userId 用户ID
     */
    List<String> getPermissionList(Integer userId);

}
