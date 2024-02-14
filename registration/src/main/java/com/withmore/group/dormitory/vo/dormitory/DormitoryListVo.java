// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.dormitory.vo.dormitory;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 宿舍信息表列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-09-11
 */
@Data
public class DormitoryListVo {

    /**
    * 宿舍信息表ID
    */
    private Integer id;

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

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建记录用户
     */
    private Integer createUser;

    /**
     * 更新记录用户
     */
    private Integer updateUser;

    /**
     * 记录有效性
     */
    private Integer mark;

}