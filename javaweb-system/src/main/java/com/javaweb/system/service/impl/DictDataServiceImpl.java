// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.DictData;
import com.javaweb.system.mapper.DictDataMapper;
import com.javaweb.system.query.DictDataQuery;
import com.javaweb.system.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典项管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-01
 */
@Service
public class DictDataServiceImpl extends BaseServiceImpl<DictDataMapper, DictData> implements IDictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 获取字典项列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DictDataQuery dictQuery = (DictDataQuery) query;
        // 查询条件
        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        // 字典ID
        if (StringUtils.isNotNull(dictQuery.getDictId())) {
            queryWrapper.eq("dict_id", dictQuery.getDictId());
        }
        // 字典项名称
        if (!StringUtils.isEmpty(dictQuery.getName())) {
            queryWrapper.like("name", dictQuery.getName());
        }
        // 字典项编码
        if (!StringUtils.isEmpty(dictQuery.getCode())) {
            queryWrapper.like("code", dictQuery.getCode());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<DictData> page = new Page<>(dictQuery.getPage(), dictQuery.getLimit());
        IPage<DictData> pageData = dictDataMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }
}
