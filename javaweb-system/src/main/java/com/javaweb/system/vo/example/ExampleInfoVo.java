// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.vo.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 演示案例表表单Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2021-03-27
 */
@Data
public class ExampleInfoVo {

    /**
     * 演示案例表ID
     */
    private Integer id;

    /**
     * 案例名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

    /**
     * 类型：1京东 2淘宝 3拼多多 4唯品会
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 出生时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 有效标记
     */
    private Integer mark;

}