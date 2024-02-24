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
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.mapper.AdSortMapper;
import com.javaweb.system.query.AdSortQuery;
import com.javaweb.system.service.IAdSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告位管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class AdSortServiceImpl extends BaseServiceImpl<AdSortMapper, AdSort> implements IAdSortService {

    @Autowired
    private AdSortMapper adSortMapper;

    /**
     * 获取广告位列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        AdSortQuery adSortQuery = (AdSortQuery) query;
        // 查询条件
        QueryWrapper<AdSort> queryWrapper = new QueryWrapper<>();
        // 广告位名称
        if (!StringUtils.isEmpty(adSortQuery.getName())) {
            queryWrapper.like("name", adSortQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<AdSort> page = new Page<>(adSortQuery.getPage(), adSortQuery.getLimit());
        IPage<AdSort> pageData = adSortMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 获取广告位列表
     *
     */
    @Override
    public JsonResult getAdSortList() {
        QueryWrapper<AdSort> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        return JsonResult.success(list(queryWrapper));
    }
}
