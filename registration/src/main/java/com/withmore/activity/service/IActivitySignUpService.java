// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.withmore.activity.entity.ActivitySignUp;
import com.javaweb.system.common.IBaseService;
import com.withmore.activity.vo.activitysignup.ActivitySignUpInfoVo;
import com.withmore.activity.vo.activitysignup.ActivitySignUpListVo;
import com.withmore.common.dto.AuthToken2CredentialDto;

import java.util.List;

/**
 * <p>
 * 活动报名信息表 服务类
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
public interface IActivitySignUpService extends IBaseService<ActivitySignUp> {
    List<ActivitySignUpListVo> getListByActivityId(Integer activityId);

    JsonResultS signUp(AuthToken2CredentialDto dto, Integer activityId);
}