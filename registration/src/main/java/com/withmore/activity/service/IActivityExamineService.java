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
import com.javaweb.system.common.IBaseService;
import com.withmore.activity.dto.UserIdentityDto;
import com.withmore.activity.entity.ActivityExamine;
import com.withmore.activity.query.ActivityClassQuery;
import com.withmore.activity.query.ActivityDataPermissionNodeQuery;
import com.withmore.activity.query.ActivityExamineListQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.entity.PermissionNode;

import java.util.List;

/**
 * <p>
 * 活动核销表 服务类
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
public interface IActivityExamineService extends IBaseService<ActivityExamine> {

    JsonResultS examine(AuthToken2CredentialDto dto, Integer activityId, AuthToken2CredentialDto examinedDto);

    JsonResultS getExamineList(AuthToken2CredentialDto dto, ActivityExamineListQuery query);
    JsonResultS getExamineListBySerial(AuthToken2CredentialDto dto, ActivityExamineListQuery query);

    JsonResultS getExamineListSelf(AuthToken2CredentialDto dto, BaseQuery query);

//    JsonResultS exportExcel(AuthToken2CredentialDto dto, Integer activityId);

    JsonResultS exportExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes);

    JsonResultS queryExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes);

    JsonResultS queryExamineNumByClass(AuthToken2CredentialDto dto, Integer activityId, ActivityClassQuery query);

    JsonResultS queryExamineNumByCollege(AuthToken2CredentialDto dto, Integer activityId, ActivityClassQuery query);

    JsonResultS queryDayHourExamine(AuthToken2CredentialDto dto, Integer activityId, String dateTime);

    /**
     * 身份认证机器身份鉴权核销方法
     *
     * @param param 鉴权参数
     * @param activityID
     */
    JsonResultS identity(UserIdentityDto param, Integer activityID);
}