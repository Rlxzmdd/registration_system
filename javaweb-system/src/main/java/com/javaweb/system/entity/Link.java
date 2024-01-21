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

import com.javaweb.system.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 友链管理表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_link")
public class Link extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 友链名称
     */
    private String name;

    /**
     * 类型：1友情链接 2合作伙伴
     */
    private Integer type;

    /**
     * 友链地址
     */
    private String url;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 栏目ID
     */
    private Integer cateId;

    /**
     * 平台：1PC站 2WAP站 3微信小程序 4APP应用
     */
    private Integer platform;

    /**
     * 友链形式：1文字链接 2图片链接
     */
    private Integer form;

    /**
     * 友链图片
     */
    private String image;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer sort;

}
