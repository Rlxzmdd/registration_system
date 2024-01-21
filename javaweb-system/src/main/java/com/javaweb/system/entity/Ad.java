// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 广告管理表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_ad")
public class Ad extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告位ID
     */
    private Integer adSortId;

    /**
     * 广告图片
     */
    private String cover;

    /**
     * 广告格式：1图片 2文字 3视频 4推荐
     */
    private Integer type;

    /**
     * 广告描述
     */
    private String description;

    /**
     * 广告内容
     */
    private String content;

    /**
     * 广告链接
     */
    private String url;

    /**
     * 广告宽度
     */
    private Integer width;

    /**
     * 广告高度
     */
    private Integer height;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 广告点击次数
     */
    private Integer viewNum;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;


}
