// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.common.utils.JsonResult;

import java.io.Serializable;
import java.util.List;


public interface IBaseService<T> extends IService<T> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     */
    JsonResult getList(BaseQuery query);

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     */
    JsonResult info(Integer id);

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     */

    Object getInfo(Serializable id);

    /**
     * 根据实体对象添加记录
     *
     * @param entity 实体对象
     */
    JsonResult add(T entity);

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     */
    JsonResult update(T entity);

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     */
    JsonResult edit(T entity);

    /**
     * 删除记录
     *
     * @param entity 实体对象
     */
    JsonResult delete(T entity);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     */
    JsonResult deleteById(Integer id);

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
    JsonResult setStatus(T entity);

    /**
     * 导出Excel
     *
     */
    List<T> exportExcel();

}
