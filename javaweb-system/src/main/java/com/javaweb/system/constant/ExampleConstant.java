// +----------------------------------------------------------------------
// | JavaWeb混编版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 演示案例表 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2021-03-27
 */
public class ExampleConstant {

    /**
     * 状态
     */
    public static Map<Integer, String> EXAMPLE_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "正常");
            put(2, "停用");
        }
    };
    /**
     * 类型
     */
    public static Map<Integer, String> EXAMPLE_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "京东");
            put(2, "淘宝");
            put(3, "拼多多");
            put(4, "唯品会");
        }
    };
}