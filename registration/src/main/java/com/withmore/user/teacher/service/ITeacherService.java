// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.teacher.service;

import com.javaweb.common.utils.JsonResult;
import com.withmore.user.teacher.entity.Teacher;
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * 教师信息表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
public interface ITeacherService extends IBaseService<Teacher> {


    Teacher login(String tchNumber, String password);
}