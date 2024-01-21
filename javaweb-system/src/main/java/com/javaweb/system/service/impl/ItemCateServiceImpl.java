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
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.ItemCate;
import com.javaweb.system.mapper.ItemCateMapper;
import com.javaweb.system.query.ItemCateQuery;
import com.javaweb.system.service.IItemCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 栏目管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class ItemCateServiceImpl extends BaseServiceImpl<ItemCateMapper, ItemCate> implements IItemCateService {

    @Autowired
    private ItemCateMapper itemCateMapper;

    /**
     * 获取栏目列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ItemCateQuery itemCateQuery = (ItemCateQuery) query;
        // 查询条件
        QueryWrapper<ItemCate> queryWrapper = new QueryWrapper<>();
        // 栏目名称
        if (!StringUtils.isEmpty(itemCateQuery.getName())) {
            queryWrapper.like("name", itemCateQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<ItemCate> pageData = itemCateMapper.selectList(queryWrapper);
        pageData.forEach(item -> {
            // 封面
            item.setCover(CommonUtils.getImageURL(item.getCover()));
        });
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ItemCate entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getCover()) && entity.getCover().contains(CommonConfig.imageURL)) {
            entity.setCover(entity.getCover().replaceAll(CommonConfig.imageURL, ""));
        }
        return super.edit(entity);
    }
}
