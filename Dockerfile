# jdk docker image
FROM openjdk:11-jre-slim
# jar 拷贝路径
COPY ./javaweb-admin/target/registration_system.jar /tmp/app.jar
# 指定工作目录
WORKDIR /tmp
EXPOSE 9031
# 执行命令
ENTRYPOINT ["java" , "-jar" , "app.jar","--spring.profiles.active=release"]

# 第一阶段：使用Maven构建Spring Boot应用
#FROM maven:3.8.4 AS builder
#WORKDIR /app
#
## 复制pom.xml并下载依赖，以便利用Docker缓存
#COPY ./pom.xml .
#RUN mvn dependency:go-offline
#
## 复制整个项目并构建jar
#COPY . .
#RUN mvn package -DskipTests
#
## 第二阶段：创建最小的JRE镜像
#FROM openjdk:11-jre-slim
#
#COPY ./javaweb-admin/target/registration_system.jar /tmp/app.jar
### 指定工作目录
#WORKDIR /tmp
#
## 暴露应用端口
#EXPOSE 9031
#
## 设置入口命令
#ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.name=application-dev_zhousic"]
