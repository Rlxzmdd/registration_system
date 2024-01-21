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
import com.withmore.activity.entity.ActivitySignUp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.activity.vo.activitysignup.ActivitySignUpListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动报名信息表 Mapper 接口
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Mapper
@DS("registration")
public interface ActivitySignUpMapper extends BaseMapper<ActivitySignUp> {
    List<ActivitySignUpListVo> getSignUpListByActivityId(
            @Param("activity_id") Integer activity_id,
            @Param("stuType") String studentTypeConstant,
            @Param("tchType") String teacherTypeConstant);
}
