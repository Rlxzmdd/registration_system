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
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Link;
import com.javaweb.system.mapper.LinkMapper;
import com.javaweb.system.query.LinkQuery;
import com.javaweb.system.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 友链管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-07
 */
@Service
public class LinkServiceImpl extends BaseServiceImpl<LinkMapper, Link> implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 获取友链列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LinkQuery linkQuery = (LinkQuery) query;
        // 查询条件
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        // 友链名称
        if (!StringUtils.isEmpty(linkQuery.getName())) {
            queryWrapper.like("name", linkQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Link> page = new Page<>(linkQuery.getPage(), linkQuery.getLimit());
        IPage<Link> pageData = linkMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            // 友链图片
            if (!StringUtils.isEmpty(x.getImage())) {
                x.setImage(CommonUtils.getImageURL(x.getImage()));
            }
            return x;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Link entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getImage()) && entity.getImage().contains(CommonConfig.imageURL)) {
            entity.setImage(entity.getImage().replaceAll(CommonConfig.imageURL, ""));
        }
        return super.edit(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(Link entity) {
        Link link = new Link();
        link.setId(entity.getId());
        link.setStatus(entity.getStatus());
        return super.setStatus(link);
    }
}
