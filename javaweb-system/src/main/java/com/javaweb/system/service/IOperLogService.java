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
import com.javaweb.system.entity.OperLog;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
public interface IOperLogService extends IBaseService<OperLog> {

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OperLog operLog);

}
