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
import com.javaweb.system.entity.Notice;

/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
public interface INoticeService extends IBaseService<Notice> {

    /**
     * 设置是否置顶
     *
     * @param entity 实体对象
     */
    JsonResult setIsTop(Notice entity);

}
