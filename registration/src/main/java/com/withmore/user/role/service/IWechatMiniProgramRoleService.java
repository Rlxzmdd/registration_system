// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.service;

import com.javaweb.common.utils.JsonResultS;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.role.entity.WechatMiniProgramRole;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 用户权限表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
public interface IWechatMiniProgramRoleService extends IBaseService<WechatMiniProgramRole> {
    /**
     * 查询用户具有角色
     *
     * @param dto 用户凭据
     * @return
     */
    JsonResultS role(AuthToken2CredentialDto dto);
}