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

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.withmore.activity.entity.ActivityItemInfo;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;

/**
 * <p>
 * 活动信息表 服务类
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
public interface IActivityItemInfoService extends IBaseService<ActivityItemInfo> {

    JsonResultS getActivityList(AuthToken2CredentialDto dto, BaseQuery query);

    JsonResultS delActivityById(AuthToken2CredentialDto dto, Integer activity_id);

    JsonResultS push(AuthToken2CredentialDto dto, ActivityItemInfo entity);

    JsonResultS getDetail(AuthToken2CredentialDto dto, Integer activityId);
}