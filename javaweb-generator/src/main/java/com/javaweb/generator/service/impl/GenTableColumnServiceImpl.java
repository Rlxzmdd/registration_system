// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.generator.service.impl;

import com.javaweb.generator.entity.GenTableColumn;
import com.javaweb.generator.mapper.GenTableColumnMapper;
import com.javaweb.generator.service.IGenTableColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    /**
     * 获取表字段信息
     *
     * @param tableId 表ID
     * @return
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }

}
