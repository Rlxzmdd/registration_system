package com.javaweb.admin;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author iszhous
 */
@SpringBootApplication(scanBasePackages = {"com.javaweb.*", "com.withmore.*"}, exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.javaweb.**.mapper", "com.withmore.**.mapper"})
@EnableTransactionManagement
// 开启定时任务支持
@EnableScheduling
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("系统成功启动");
    }
}
