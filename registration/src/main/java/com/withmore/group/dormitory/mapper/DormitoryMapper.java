// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.dormitory.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.group.dormitory.entity.Dormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 宿舍信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-09-11
 */
@DS("registration")
public interface DormitoryMapper extends BaseMapper<Dormitory> {

}
