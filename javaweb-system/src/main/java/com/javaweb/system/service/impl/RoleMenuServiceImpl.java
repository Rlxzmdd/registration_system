// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.RoleMenu;
import com.javaweb.system.mapper.RoleMenuMapper;
import com.javaweb.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    /**
     * 根据角色ID删除角色菜单关系数据
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public boolean deleteRoleMenus(Integer roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        boolean result = remove(queryWrapper);
        return result;
    }
}
