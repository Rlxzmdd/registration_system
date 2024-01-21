// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CommonConfig {

    /**
     * 图片域名
     */
    public static String imageURL;

    /**
     * 是否演示环境：true是,false否
     */
    public static boolean appDebug;

    /**
     * 图片域名赋值
     *
     * @param url 域名地址
     */
    @Value("${javaweb.image-url}")
    public void setImageURL(String url) {
        imageURL = url;
    }

    /**
     * 是否演示模式
     *
     * @param debug
     */
    @Value("${javaweb.app-debug}")
    public void setAppDebug(boolean debug) {
        appDebug = debug;
    }
}
