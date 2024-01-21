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
 * <p>
 * 演示案例表查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2021-03-27
 */
@Data
public class ExampleQuery extends BaseQuery {

    /**
     * 案例名称
     */
    private String name;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

    /**
     * 类型：1京东 2淘宝 3拼多多 4唯品会
     */
    private Integer type;

}
