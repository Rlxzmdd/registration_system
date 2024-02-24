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
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.Layout;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.mapper.LayoutMapper;
import com.javaweb.system.query.LayoutQuery;
import com.javaweb.system.service.ILayoutService;
import com.javaweb.system.vo.layout.LayoutListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 页面布局管理 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class LayoutServiceImpl extends BaseServiceImpl<LayoutMapper, Layout> implements ILayoutService {

    @Autowired
    private LayoutMapper layoutMapper;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 获取布局描述
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LayoutQuery layoutQuery = (LayoutQuery) query;
        // 查询条件
        QueryWrapper<Layout> queryWrapper = new QueryWrapper<>();
        // 站点ID
        if (StringUtils.isNotNull(layoutQuery.getItemId())) {
            queryWrapper.like("item_id", layoutQuery.getItemId());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Layout> page = new Page<>(layoutQuery.getPage(), layoutQuery.getLimit());
        IPage<Layout> pageData = layoutMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            LayoutListVo layoutListVo = Convert.convert(LayoutListVo.class, x);
            // 站点名称
            if (StringUtils.isNotNull(x.getItemId())) {
                Item item = itemMapper.selectById(x.getItemId());
                if (item != null) {
                    layoutListVo.setItemName(item.getName());
                }
            }
            // 图片处理
            if (!StringUtils.isEmpty(x.getImage())) {
                layoutListVo.setImage(CommonUtils.getImageURL(x.getImage()));
            }
            return layoutListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取布局详情
     *
     * @param id 记录ID
     */
    @Override
    public Object getInfo(Serializable id) {
        Layout layout = (Layout) super.getInfo(id);
        // 图片处理
        if (!StringUtils.isEmpty(layout.getImage())) {
            layout.setImage(CommonUtils.getImageURL(layout.getImage()));
        }
        return layout;
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(Layout entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getImage()) && entity.getImage().contains(CommonConfig.imageURL)) {
            entity.setImage(entity.getImage().replaceAll(CommonConfig.imageURL, ""));
        }
        return super.edit(entity);
    }
}
