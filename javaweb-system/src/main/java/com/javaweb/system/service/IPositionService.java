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

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.IBaseService;
import com.javaweb.system.entity.Position;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
public interface IPositionService extends IBaseService<Position> {

    /**
     * 获取岗位列表
     *
     */
    JsonResult getPositionList();

}
