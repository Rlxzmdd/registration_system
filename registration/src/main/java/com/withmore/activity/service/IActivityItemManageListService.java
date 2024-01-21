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
import com.withmore.activity.entity.ActivityItemManageList;
import com.javaweb.system.common.IBaseService;
import com.withmore.activity.query.ActivityManageAddQuery;
import com.withmore.activity.query.ActivityManageListQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;

/**
 * <p>
 * 活动管理员信息表 服务类
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
public interface IActivityItemManageListService extends IBaseService<ActivityItemManageList> {
    JsonResultS getManageListSelf(AuthToken2CredentialDto dto, ActivityManageListQuery query);

    JsonResultS addManager(AuthToken2CredentialDto dto, ActivityManageAddQuery query);

    JsonResultS delManager(AuthToken2CredentialDto dto, ActivityManageAddQuery query);

}