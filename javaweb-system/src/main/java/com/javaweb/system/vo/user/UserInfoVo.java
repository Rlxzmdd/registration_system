// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.Role;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 用户信息Vo
 */
@Data
public class UserInfoVo {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色列表
     */
    private List<Role> roles;

    /**
     * 菜单权限
     */
    private List<Menu> authorities;

    /**
     * 获取节点权限列表
     */
    private List<String> permissionList;

}
