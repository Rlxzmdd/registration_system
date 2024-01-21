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
import com.javaweb.system.entity.Item;

import java.util.List;

/**
 * <p>
 * 站点配置表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
public interface IItemService extends IBaseService<Item> {

    /**
     * 获取站点列表
     *
     * @return
     */
    List<Item> getItemList();

}
