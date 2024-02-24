// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.generator.entity.GenTableColumn;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    /**
     * 根据表名查询列信息
     *
     * @param tableName 数据表名
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 插入数据表列
     *
     * @param TableColumn 数据表列
     */
    int insertGenTableColumn(GenTableColumn TableColumn);

    /**
     * 获取表字段列表
     *
     * @param tableId 表ID
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);

    /**
     * 修改业务表字段
     *
     * @param TableColumn 表字段
     */
    int updateGenTableColumn(GenTableColumn TableColumn);

}
