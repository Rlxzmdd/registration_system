// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmore.activity.entity.ActivityExamine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.activity.query.ActivityClassQuery;
import com.withmore.activity.vo.activity.*;
import com.withmore.activity.vo.activityexamine.ActivityExamineInfoVo;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动核销表 Mapper 接口
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Mapper
@DS("registration")
public interface ActivityExamineMapper extends BaseMapper<ActivityExamine> {
    ActivityExamineInfoVo selectOneExamine(
            @Param("activityItemId") Integer activityItemId,
            @Param("examinedNumber") String examinedNumber,
            @Param("examinedType") String examinedType,
            @Param("mark") Integer mark
    );

    IPage<ActivityExamineVo> getExamineListSelf(
            Page<ActivityExamineVo> page,
            @Param("examinedNumber") String examinedNumber,
            @Param("examinedType") String examinedType,
            @Param("mark") Integer mark
    );

    IPage<ActivityExamineVo> getExamineList(
            Page<ActivityExamineVo> page,
            @Param("activityId") Integer activityId,
            @Param("mark") Integer mark
    );

    IPage<ActivityExamineVo> getExamineListBySerial(
            Page<ActivityExamineVo> page,
            @Param("activityId") Integer activityId,
            @Param("serialNum") Integer serialNum,
            @Param("mark") Integer mark
    );


    void insertExamine(ActivityExamine entigetExamineListBySerialty);

    List<ActivityClassExamineExcelVo> exportExamineByNode(Integer activityId, List<PermissionNode> nodes);

    IPage<ActivityClassExamineExcelVo> queryExamineByNode(
            Page<ActivityClassExamineExcelVo> page,
            Integer activityId,
            List<PermissionNode> nodes
    );

    List<ActivityExamineHourVo> queryDayHourExamine(
            @Param("activityId")  Integer activityId,
            @Param("dateTime") String dateTime
    );

    List<ActivityClassExamineVo> queryExamineNumByClass(
            @Param("activityId")  Integer activityId,
            @Param("query") ActivityClassQuery query
    );

    List<ActivityCollegeExamineVo> queryExamineNumByCollege(
            @Param("activityId")  Integer activityId,
            @Param("query") ActivityClassQuery query
    );
}
