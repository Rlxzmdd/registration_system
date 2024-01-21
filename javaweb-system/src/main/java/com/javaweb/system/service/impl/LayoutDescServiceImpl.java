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
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.mapper.LayoutDescMapper;
import com.javaweb.system.query.LayoutDescQuery;
import com.javaweb.system.service.ILayoutDescService;
import com.javaweb.system.vo.layoutdesc.LayoutDescListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 布局描述管理 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class LayoutDescServiceImpl extends BaseServiceImpl<LayoutDescMapper, LayoutDesc> implements ILayoutDescService {

    @Autowired
    private LayoutDescMapper layoutDescMapper;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 获取布局描述列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LayoutDescQuery layoutDescQuery = (LayoutDescQuery) query;
        // 查询条件
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        // 布局描述
        if (!StringUtils.isEmpty(layoutDescQuery.getLocDesc())) {
            queryWrapper.like("loc_desc", layoutDescQuery.getLocDesc());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<LayoutDesc> page = new Page<>(layoutDescQuery.getPage(), layoutDescQuery.getLimit());
        IPage<LayoutDesc> pageData = layoutDescMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            LayoutDescListVo layoutDescListVo = Convert.convert(LayoutDescListVo.class, x);
            // 站点名称
            if (StringUtils.isNotNull(x.getItemId())) {
                Item item = itemMapper.selectById(x.getItemId());
                if (item != null) {
                    layoutDescListVo.setItemName(item.getName());
                }
            }
            return layoutDescListVo;
        });
        return JsonResult.success(pageData);
    }
}
