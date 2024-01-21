// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 确保应用退出时关闭后台线程
 */
@Component
public class ShutdownManager {

    private static final Logger logger = LoggerFactory.getLogger("sys-user");

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            logger.info("====关闭后台任务任务线程池====");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
