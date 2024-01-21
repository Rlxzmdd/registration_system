// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.todo.dto.NoticePushDto;
import com.withmore.event.todo.entity.TodoNotice;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 待办事项-通知信息表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface ITodoNoticeService extends IBaseService<TodoNotice> {


    /**
     * 小程序用户推送通知
     *
     * @param notice 通知详情
     * @param dto    用户凭据
     * @return
     */
    JsonResultS release(NoticePushDto notice, AuthToken2CredentialDto dto);

    /**
     * 小程序用户获取通知列表
     * 具有权限节点限制
     *
     * @param baseQuery 分页参数
     * @param dto       用户凭据
     * @return
     */
    JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto);
}