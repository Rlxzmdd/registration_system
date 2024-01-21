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
import com.javaweb.system.entity.LoginLog;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
public interface ILoginLogService extends IBaseService<LoginLog> {

    /**
     * 创建系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    void insertLoginLog(LoginLog loginLog);

}
