// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.mapper;

import com.javaweb.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户ID获取权限列表
     *
     * @param userId 用户ID
     * @param pid    上级ID
     * @return
     */
    List<Menu> getPermissionsListByUserId(@RequestParam("userId") Integer userId, @RequestParam("pid") Integer pid);

    /**
     * 获取所有权限
     *
     * @return
     */
    List<Menu> getPermissionsAll();

    /**
     * 获取用户权限节点
     *
     * @param userId 用户ID
     * @return
     */
    List<String> getPermissionList(Integer userId);

}
