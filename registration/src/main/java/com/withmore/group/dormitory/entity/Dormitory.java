// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 宿舍信息表
 * </p>
 *
 * @author Cheney
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("entity_dormitory")
public class Dormitory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 宿舍人数
     */
    private Integer max;

    /**
     * 所在校区
     */
    private String campus;

    /**
     * 所属片区
     */
    private String block;

    /**
     * 楼栋
     */
    private String cell;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 房号
     */
    private String room;

    /**
     * 房间简称
     */
    private String abbr;

    /**
     * 宿舍长学号
     */
    private String managerNumber;

}