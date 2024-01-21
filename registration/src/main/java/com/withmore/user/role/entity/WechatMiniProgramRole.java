// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_role")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class WechatMiniProgramRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户学号/工号
     */
    private String userNumber;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 角色ID
     */
    private Integer roleId;

}