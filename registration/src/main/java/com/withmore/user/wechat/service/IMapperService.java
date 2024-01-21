// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.service;

import com.javaweb.common.utils.JsonResultS;
import com.withmore.user.wechat.entity.Mapper;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 小程序kv映射表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-08-24
 */
public interface IMapperService extends IBaseService<Mapper> {
    /**
     * 获取KV 映射表
     *
     * @return
     */
    JsonResultS simple();
}