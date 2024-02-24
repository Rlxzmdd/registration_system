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

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Ad;
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.mapper.AdMapper;
import com.javaweb.system.mapper.AdSortMapper;
import com.javaweb.system.query.AdQuery;
import com.javaweb.system.service.IAdService;
import com.javaweb.system.vo.ad.AdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class AdServiceImpl extends BaseServiceImpl<AdMapper, Ad> implements IAdService {

    @Autowired
    private AdMapper adMapper;
    @Autowired
    private AdSortMapper adSortMapper;

    /**
     * 获取广告列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        AdQuery adQuery = (AdQuery) query;
        // 查询条件
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        // 广告标题
        if (!StringUtils.isEmpty(adQuery.getTitle())) {
            queryWrapper.like("title", adQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Ad> page = new Page<>(adQuery.getPage(), adQuery.getLimit());
        IPage<Ad> pageData = adMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            AdListVo adListVo = Convert.convert(AdListVo.class, x);
            // 封面
            if (!StringUtils.isEmpty(x.getCover())) {
                adListVo.setCover(CommonUtils.getImageURL(x.getCover()));
            }
            // 广告位描述
            AdSort adSort = adSortMapper.selectById(x.getAdSortId());
            if (adSort != null) {
                adListVo.setAdSortName(String.format("%s => %s", adSort.getDescription(), adSort.getLocId().toString()));
            }
            return adListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(Ad entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getCover()) && entity.getCover().contains(CommonConfig.imageURL)) {
            entity.setCover(entity.getCover().replaceAll(CommonConfig.imageURL, ""));
        }
        return super.edit(entity);
    }
}
