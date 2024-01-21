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
 * 广告查询条件
 */
@Data
public class AdQuery extends BaseQuery {

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告位ID
     */
    private Integer adSortId;

}
