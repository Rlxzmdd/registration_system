// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.classes.service;

import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.IBaseService;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.group.classes.entity.Classes;

/**
 * <p>
 * 班级信息表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface IClassesService extends IBaseService<Classes> {
    /**
     * 分页查询可访问的班级列表简略信息
     *
     * @param baseQuery 分页参数
     * @param dto       用户凭据
     */
    JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto);

    /**
     * 根据班级ID 查询班级简略信息
     *
     * @param classesId 班级ID
     * @param dto       用户凭据
     */
    JsonResultS simple(Integer classesId, AuthToken2CredentialDto dto);

    /**
     * 根据班级ID 查询班级负责人联系方式
     *
     * @param id 班级ID
     */
    JsonResultS contact(String id);

    /**
     * 获取所有学院、专业、班级的字典
     */
    JsonResultS dict();
}