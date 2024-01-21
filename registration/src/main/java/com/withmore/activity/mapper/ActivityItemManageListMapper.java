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
import com.withmore.activity.entity.ActivityItemManageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.activity.vo.activity.ActivityInfoManageVo;
import com.withmore.activity.vo.activity.ActivityManageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动管理员信息表 Mapper 接口
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Mapper
@DS("registration")
public interface ActivityItemManageListMapper extends BaseMapper<ActivityItemManageList> {
    IPage<ActivityManageVo> getManagerListByActivityId(
            Page<ActivityManageVo> page,
            @Param("activityId") Integer activityId,
            @Param("stuType") String studentTypeConstant,
            @Param("tchType") String teacherTypeConstant);
}
