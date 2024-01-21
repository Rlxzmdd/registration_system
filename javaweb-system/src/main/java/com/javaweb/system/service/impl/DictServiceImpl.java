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
import com.javaweb.system.entity.Dict;
import com.javaweb.system.mapper.DictMapper;
import com.javaweb.system.query.DictQuery;
import com.javaweb.system.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-01
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 获取字典列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DictQuery dicTypeQuery = (DictQuery) query;
        // 查询条件
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        // 字典名称
        if (!StringUtils.isEmpty(dicTypeQuery.getName())) {
            queryWrapper.like("name", dicTypeQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Dict> page = new Page<>(dicTypeQuery.getPage(), dicTypeQuery.getLimit());
        IPage<Dict> pageData = dictMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }
}
