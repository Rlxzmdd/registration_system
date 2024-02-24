// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.admin.service;

import com.javaweb.admin.entity.MemberLevel;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 会员级别表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-04
 */
public interface IMemberLevelService extends IBaseService<MemberLevel> {

    /**
     * 获取会员等级列表
     *
     */
    JsonResult getMemberLevelList();

}
