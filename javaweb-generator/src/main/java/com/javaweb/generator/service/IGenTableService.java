// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.generator.entity.GenTable;
import com.javaweb.generator.query.GenTableQuery;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
public interface IGenTableService extends IService<GenTable> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param genTableQuery 查询条件
     */
    JsonResult getList(GenTableQuery genTableQuery);

    /**
     * 获取数据库表
     *
     * @param query 查询条件
     */
    IPage<GenTable> genDbTableList(GenTableQuery query);

    /**
     * 查询据库列表
     *
     * @param tableNames 表数组
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    void importGenTable(List<GenTable> tableList);

    /**
     * 根据表ID获取表信息
     *
     * @param tableId 表ID
     */
    GenTable selectGenTableById(Integer tableId);

    /**
     * 业务表保存参数校验
     *
     * @param Table 生成表
     */
    void validateEdit(GenTable Table);

    /**
     * 更新业务表信息
     *
     * @param Table 业务表
     */
    void updateGenTable(GenTable Table);

    /**
     * 生成代码
     *
     * @param tableNames 数据表
     */
    JsonResult generatorCode(String[] tableNames);

    /**
     * 删除记录
     *
     * @param ids 业务表ID
     */
    JsonResult delete(Integer[] ids);

}
