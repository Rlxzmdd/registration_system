// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.javaweb.system.common.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 活动报名信息表
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("activity_sign_up")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class ActivitySignUp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityItemId;

    /**
     * 报名用户number
     */
    private String userNumber;

    /**
     * 
     */
    private String userType;

    /**
     * 报名时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signUpTime;

    /**
     * 报名是否通过
     */
    private Integer isApproval;

}