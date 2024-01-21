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

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.IBaseService;
import com.javaweb.system.entity.Level;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.vo.level.LevelInfoVo;

import java.util.List;

/**
 * <p>
 * 职级表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
public interface ILevelService extends IBaseService<Level> {

    /**
     * 获取职级列表
     *
     * @return
     */
    JsonResult getLevelList();

    /**
     * 导出Excel数据
     *
     * @param levelQuery 查询条件
     * @return
     */
    List<LevelInfoVo> exportExcel(LevelQuery levelQuery);

}
