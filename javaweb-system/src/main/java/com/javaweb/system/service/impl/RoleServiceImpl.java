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

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.RedisUtils;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.Role;
import com.javaweb.system.entity.RoleMenu;
import com.javaweb.system.mapper.RoleMapper;
import com.javaweb.system.mapper.RoleMenuMapper;
import com.javaweb.system.query.RoleQuery;
import com.javaweb.system.service.IMenuService;
import com.javaweb.system.service.IRoleMenuService;
import com.javaweb.system.service.IRoleService;
import com.javaweb.system.vo.role.RoleListVo;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取角色列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        RoleQuery roleQuery = (RoleQuery) query;
        // 查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 角色名称
        if (!StringUtils.isEmpty(roleQuery.getName())) {
            queryWrapper.like("name", roleQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        IPage<Role> pageData = roleMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            RoleListVo roleListVo = Convert.convert(RoleListVo.class, x);
            // TODO...
            return roleListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取角色列表
     *
     */
    @Override
    public JsonResult getRoleList() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        return JsonResult.success(list(queryWrapper));
    }

    /**
     * 获取菜单列表
     *
     * @param roleId 角色ID
     */
    @Override
    public JsonResult getMenuList(Integer roleId) {
        List<Menu> menuList = menuService.getMenuAll();
        if (!menuList.isEmpty()) {
            // 获取角色菜单列表
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", roleId);
            queryWrapper.select("menu_id");
            List<RoleMenu> roleMenuList = roleMenuMapper.selectList(queryWrapper);
            List<Integer> menuIds = roleMenuList.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
            menuList.forEach(item -> {
                if (menuIds.contains(item.getId())) {
                    item.setChecked(true);
                    item.setOpen(true);
                }
            });
        }
        return JsonResult.success(menuList);
    }

    /**
     * 清理用户在ShiroCache中的缓存
     * 在为角色增减相应的权限不清除Redis用户缓存将会出现权限不对等的情况
     */
    private void cleanShiroUserPermissionCache() {
        // 获取前缀为 shiro:cache: 的redis 用户登陆权限等缓存Keys
        Set<String> keys = redisUtils.keys(RedisCacheManager.DEFAULT_CACHE_KEY_PREFIX + "*");
        redisUtils.del(keys.toArray(new String[0]));
    }

    /**
     * 保存角色菜单权限数据
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID几个
     */
    @Override
    public JsonResult savePermission(Integer roleId, String[] menuIds) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        // 角色ID校验
        if (StringUtils.isNull(roleId)) {
            return JsonResult.error("角色ID不能位空");
        }
        // 同步删除角色菜单关系数据
        roleMenuService.deleteRoleMenus(roleId);
        // 插入新的角色菜单关系数据
        List<RoleMenu> roleMenuList = new ArrayList<>();
        if (!StringUtils.isEmpty(menuIds)) {
            for (String menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(Integer.valueOf(menuId));
                roleMenuList.add(roleMenu);
            }
        }
        // 批量插入角色菜单关系数据
        roleMenuService.saveBatch(roleMenuList);
        // 改变权限后清理所有缓存中的用户权限
        cleanShiroUserPermissionCache();
        return JsonResult.success("权限保存成功");
    }
}
