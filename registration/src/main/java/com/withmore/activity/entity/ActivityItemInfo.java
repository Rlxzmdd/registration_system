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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 活动信息表
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("activity_item_info")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class ActivityItemInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动小标题
     */
    private String location;

    /**
     * 创建活动者number
     */
    private String creatorNumber;

    /**
     * 
     */
    private String creatorType;

    /**
     * 是否长期开放
     */
    private Integer isDurative;

    /**
     * 是否需要报名
     */
    private Integer isApply;

    /**
     * 是否允许重新核销
     */
    private Integer isRepeat;

    /**
     * 是否允许转发
     */
    private Integer isShare;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 报名开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTimeStart;

    /**
     * 报名结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTimeEnd;

    /**
     * 活动开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeStart;

    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeEnd;

}