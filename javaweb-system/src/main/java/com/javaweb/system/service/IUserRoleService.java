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
import com.javaweb.system.entity.UserRole;

/**
 * <p>
 * 人员角色表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
public interface IUserRoleService extends IBaseService<UserRole> {

    /**
     * 插入用户角色关系数据
     *
     * @param userId  用户ID
     * @param roleIds 角色ID
     */
    void insertUserRole(Integer userId, Integer[] roleIds);

    /**
     * 根据用户ID删除用户角色关系数据
     *
     * @param userId 用户ID
     */
    void deleteUserRole(Integer userId);

}
