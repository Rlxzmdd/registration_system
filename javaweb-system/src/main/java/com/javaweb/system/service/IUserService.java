// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.ResetPwdDto;
import com.javaweb.system.dto.UpdatePwdDto;
import com.javaweb.system.dto.UpdateUserInfoDto;
import com.javaweb.system.entity.User;
import com.javaweb.system.query.UserQuery;

/**
 * <p>
 * 后台用户管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
public interface IUserService extends IService<User> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param userQuery 查询条件
     */
    JsonResult getList(UserQuery userQuery);

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     */
    JsonResult edit(User entity);

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     */
    JsonResult deleteByIds(Integer[] ids);

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    JsonResult setStatus(User entity);

    /**
     * 获取用户信息
     *
     */
    JsonResult getUserInfo();

    /**
     * 修改密码
     *
     * @param updatePwdDto 参数
     */
    JsonResult updatePwd(UpdatePwdDto updatePwdDto);

    /**
     * 更新个人资料
     *
     * @param updateUserInfoDto 参数
     */
    JsonResult updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    /**
     * 重置密码
     *
     * @param resetPwdDto 参数
     */
    JsonResult resetPwd(ResetPwdDto resetPwdDto);
}
