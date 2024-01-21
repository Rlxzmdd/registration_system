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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.mapper.MenuMapper;
import com.javaweb.system.query.MenuQuery;
import com.javaweb.system.service.IMenuService;
import com.javaweb.system.utils.ShiroUtils;
import com.javaweb.system.vo.menu.MenuInfoVo;
import com.javaweb.system.vo.menu.MenuListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MenuQuery menuQuery = (MenuQuery) query;
        // 查询条件
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        // 上级ID
//        if (StringUtils.isNotNull(menuQuery.getPid()) && menuQuery.getPid() > 0) {
//            queryWrapper.eq("pid", menuQuery.getPid());
//        } else {
//            queryWrapper.eq("pid", 0);
//        }
        // 菜单名称
        if (!StringUtils.isEmpty(menuQuery.getTitle())) {
            queryWrapper.like("title", menuQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                MenuListVo menuListVo = new MenuListVo();
                BeanUtils.copyProperties(item, menuListVo);
//                // 是否有子级
//                if (item.getType() == 0) {
//                    menuListVo.setHasChildren(true);
//                }
                menuListVoList.add(menuListVo);
            });
        }
        return JsonResult.success(menuListVoList);
    }

    /**
     * 获取菜单详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Menu menu = (Menu) super.getInfo(id);

        // 拷贝属性
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        BeanUtils.copyProperties(menu, menuInfoVo);

        // 获取菜单节点列表
        List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getPid, menu.getId())
                .eq(Menu::getType, 1)
                .eq(Menu::getMark, 1));
        List<Integer> checkedList = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                checkedList.add(item.getSort());
            });
        }
        menuInfoVo.setCheckedList(checkedList.toArray(new Integer[]{}));
        return menuInfoVo;
    }

    /**
     * 添加或编辑菜单
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Menu entity) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        if (entity == null) {
            return JsonResult.error("实体对象不存在");
        }
        boolean result = false;
        if (entity.getId() != null && entity.getId() > 0) {
            // 修改记录
            entity.setUpdateUser(ShiroUtils.getUserId());
            entity.setUpdateTime(DateUtils.now());
            result = this.updateById(entity);
        } else {
            // 新增记录
            entity.setCreateUser(ShiroUtils.getUserId());
            entity.setCreateTime(DateUtils.now());
            entity.setMark(1);
            result = this.save(entity);
        }
        if (!result) {
            return JsonResult.error();
        }
        // 权限节点处理
        boolean isFunc = false;
        if (entity.getType() == 0 && !StringUtils.isEmpty(entity.getPath()) && entity.getCheckedList().length > 0) {
            String[] strings = entity.getPath().split("/");
            // 模块名称
            String moduleName = strings[strings.length - 1];
            // 目标标题
            String moduleTitle = entity.getTitle().replace("管理", "");
            // 删除已有节点
            menuMapper.delete(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, entity.getId()).eq(Menu::getType, 1));
            isFunc = true;
            // 遍历权限节点
            for (Integer item : entity.getCheckedList()) {
                Menu funcInfo = new Menu();
                funcInfo.setPid(entity.getId());
                funcInfo.setType(1);
                funcInfo.setStatus(1);
                funcInfo.setSort(item);
                funcInfo.setTarget(entity.getTarget());
                if (item.equals(1)) {
                    // 查看
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle(String.format("查询%s", moduleTitle));
                    funcInfo.setPermission(String.format("sys:%s:index", moduleName));
                } else if (item.equals(5)) {
                    // 添加
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle(String.format("添加%s", moduleTitle));
                    funcInfo.setPermission(String.format("sys:%s:add", moduleName));
                } else if (item.equals(10)) {
                    // 修改
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle(String.format("修改%s", moduleTitle));
                    funcInfo.setPermission(String.format("sys:%s:edit", moduleName));
                } else if (item.equals(15)) {
                    // 删除
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle(String.format("删除%s", moduleTitle));
                    funcInfo.setPermission(String.format("sys:%s:delete", moduleName));
                } else if (item.equals(20)) {
                    // 状态
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("设置状态");
                    funcInfo.setPermission(String.format("sys:%s:status", moduleName));
                } else if (item.equals(25)) {
                    // 批量删除
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("批量删除");
                    funcInfo.setPermission(String.format("sys:%s:dall", moduleName));
                } else if (item.equals(30)) {
                    // 全部展开
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("全部展开");
                    funcInfo.setPermission(String.format("sys:%s:expand", moduleName));
                } else if (item.equals(35)) {
                    // 全部折叠
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("全部折叠");
                    funcInfo.setPermission(String.format("sys:%s:collapse", moduleName));
                } else if (item.equals(40)) {
                    // 添加子级
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("添加子级");
                    funcInfo.setPermission(String.format("sys:%s:addz", moduleName));
                } else if (item.equals(45)) {
                    // 导出数据
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("导出数据");
                    funcInfo.setPermission(String.format("sys:%s:export", moduleName));
                } else if (item.equals(50)) {
                    // 导入数据
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("导入数据");
                    funcInfo.setPermission(String.format("sys:%s:import", moduleName));
                } else if (item.equals(55)) {
                    // 分配权限
                    Menu menuInfo = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                            .eq(Menu::getPid, entity.getId())
                            .eq(Menu::getType, 1)
                            .eq(Menu::getSort, item)
                            .eq(Menu::getMark, 1)
                            .last("limit 1"));
                    if (menuInfo != null) {
                        funcInfo.setId(menuInfo.getId());
                        funcInfo.setUpdateUser(ShiroUtils.getUserId());
                        funcInfo.setUpdateTime(DateUtils.now());
                    }
                    funcInfo.setTitle("分配权限");
                    funcInfo.setPermission(String.format("sys:%s:permission", moduleName));
                }
                if (StringUtils.isEmpty(funcInfo.getTitle())) {
                    continue;
                }
                if (StringUtils.isNull(funcInfo.getId())) {
                    // 创建
                    menuMapper.insert(funcInfo);
                } else {
                    // 更新
                    menuMapper.updateById(funcInfo);
                }
            }
        }
        return JsonResult.success(isFunc, "操作成功");
    }

    /**
     * 获取导航菜单
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<Menu> getMenuList(Integer userId) {
        List<Menu> menuList = null;
        if (userId.equals(1)) {
            menuList = getChildrenMenuAll(0);
        } else {
            menuList = menuMapper.getPermissionsListByUserId(userId, 0);
            if (!StringUtils.isNull(menuList)) {
                for (Menu menu : menuList) {
                    List<Menu> childrenList = getChildrenMenuByPid(userId, menu.getId());
                    menu.setChildren(childrenList);
                }
            }
        }
        return menuList;
    }

    /**
     * 根据父级ID获取子级菜单
     *
     * @param pid 上级ID
     * @return
     */
    public List<Menu> getChildrenMenuAll(Integer pid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("status", 1);
        // 只取菜单一级
        queryWrapper.eq("type", 0);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Menu> menuList = list(queryWrapper);
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                List<Menu> childrenList = getChildrenMenuAll(item.getId());
                item.setChildren(childrenList);
            });
        }
        return menuList;
    }

    /**
     * 根据上级ID获取子级菜单
     *
     * @param userId 用户ID
     * @param pid    上级ID
     * @return
     */
    public List<Menu> getChildrenMenuByPid(Integer userId, Integer pid) {
        List<Menu> menuList = menuMapper.getPermissionsListByUserId(userId, pid);
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                List<Menu> childrenList = getChildrenMenuByPid(userId, item.getId());
                item.setChildren(childrenList);
            });
        }
        return menuList;
    }

    /**
     * 获取所有菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenuAll() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.le("type", 2);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Menu> menuList = list(queryWrapper);
        return menuList;
    }

    /**
     * 获取权限节点列表
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<String> getPermissionList(Integer userId) {
        List<String> permissionList = new ArrayList<>();
        if (userId.equals(1)) {
            // 超级管理员
            List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                    .eq(Menu::getType, 1)
                    .eq(Menu::getStatus, 1));
            if (!menuList.isEmpty()) {
                menuList.forEach(item -> {
                    permissionList.add(item.getPermission());
                });
            }
        } else {
            // 其他
            List<String> stringList = menuMapper.getPermissionList(userId);
            if (!stringList.isEmpty()) {
                for (String permission : stringList) {
                    permissionList.add(permission);
                }
            }
        }
        return permissionList;
    }
}
