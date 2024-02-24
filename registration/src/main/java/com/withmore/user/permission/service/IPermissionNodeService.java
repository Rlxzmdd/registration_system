// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.permission.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.entity.PermissionNode;

/**
 * <p>
 * 权限节点表-待办事项可视权限 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-27
 */
public interface IPermissionNodeService extends IBaseService<PermissionNode> {
    /**
     * 获取用户自身权限节点
     *
     * @param dto 用户凭据
     */
    JsonResultS list(AuthToken2CredentialDto dto);

    /**
     * 获取权限下可访问的所有节点筛选列表
     * @param dto 用户凭据
     */
    JsonResultS filter(AuthToken2CredentialDto dto);
}