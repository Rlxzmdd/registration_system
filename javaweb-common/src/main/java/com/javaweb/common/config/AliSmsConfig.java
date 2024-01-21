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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里短信配置类
 */
// 让Spring启动的时候扫描到该类，并添加到Spring容器中
@Configuration
// 设置前缀
@ConfigurationProperties(prefix = "spring.alisms")
@Data
public class AliSmsConfig {

    /**
     * KEY
     */
    private String accessKeyId;

    /**
     * 密钥
     */
    private String accessKeySecret;

    /**
     * 区域ID
     */
    private String regionId;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信模板ID
     */
    private String templateCode;

}
