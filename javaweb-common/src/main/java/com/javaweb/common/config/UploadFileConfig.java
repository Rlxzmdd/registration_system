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

/**
 * 文件上传配置
 */
@Configuration
@Data
public class UploadFileConfig {
    /**
     * 上传目录
     */
    public static String uploadFolder;
    /**
     * 访问路径
     */
    public static String staticAccessPath;
    /**
     * 上传服务器的映射文件夹
     */
    public static String accessPath;

    @Value("${file.uploadFolder}")
    public void setUploadFolder(String path) {
        uploadFolder = path;
    }

    @Value("${file.staticAccessPath}")
    public void setStaticAccessPath(String path) {
        staticAccessPath = path;
    }

    @Value("${file.accessPath}")
    public void setAccessPath(String path) {
        accessPath = path;
    }
}
