// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.admin.entity.MemberLevel;
import com.javaweb.admin.mapper.MemberLevelMapper;
import com.javaweb.admin.query.MemberLevelQuery;
import com.javaweb.admin.service.IMemberLevelService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员级别表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-04
 */
@Service
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevelMapper, MemberLevel> implements IMemberLevelService {

    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 获取会员等级列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MemberLevelQuery memberLevelQuery = (MemberLevelQuery) query;
        // 查询条件
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        // 会员等级名称
        if (!StringUtils.isEmpty(memberLevelQuery.getName())) {
            queryWrapper.like("name", memberLevelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<MemberLevel> page = new Page<>(memberLevelQuery.getPage(), memberLevelQuery.getLimit());
        IPage<MemberLevel> pageData = memberLevelMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 获取会员等级列表
     *
     */
    @Override
    public JsonResult getMemberLevelList() {
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        return JsonResult.success(list(queryWrapper));
    }
}
