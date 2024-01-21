// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;

/**
 * 站点查询条件
 */
@Data
public class ItemQuery extends BaseQuery {

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点类型
     */
    private Integer type;

    /**
     * 站点状态
     */
    private Integer status;

}
