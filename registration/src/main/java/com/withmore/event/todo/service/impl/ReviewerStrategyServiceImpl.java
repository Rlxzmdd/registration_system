// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.event.todo.constant.ReviewerStrategyConstant;
import com.withmore.event.todo.entity.ReviewerStrategy;
import com.withmore.event.todo.mapper.ReviewerStrategyMapper;
import com.withmore.event.todo.query.ReviewerStrategyQuery;
import com.withmore.event.todo.service.IReviewerStrategyService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.event.todo.vo.reviewerstrategy.ReviewerStrategyInfoVo;
import com.withmore.event.todo.vo.reviewerstrategy.ReviewerStrategyListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
  * <p>
  * 表单审核策略表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-08-25
  */
@Service
public class ReviewerStrategyServiceImpl extends BaseServiceImpl<ReviewerStrategyMapper, ReviewerStrategy> implements IReviewerStrategyService {

    @Autowired
    private ReviewerStrategyMapper reviewerStrategyMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ReviewerStrategyQuery reviewerStrategyQuery = (ReviewerStrategyQuery) query;
        // 查询条件
        QueryWrapper<ReviewerStrategy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ReviewerStrategy> page = new Page<>(reviewerStrategyQuery.getPage(), reviewerStrategyQuery.getLimit());
        IPage<ReviewerStrategy> pageData = reviewerStrategyMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ReviewerStrategyListVo reviewerStrategyListVo = Convert.convert(ReviewerStrategyListVo.class, x);
            return reviewerStrategyListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ReviewerStrategy entity = (ReviewerStrategy) super.getInfo(id);
        // 返回视图Vo
        ReviewerStrategyInfoVo reviewerStrategyInfoVo = new ReviewerStrategyInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, reviewerStrategyInfoVo);
        return reviewerStrategyInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ReviewerStrategy entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
            entity.setUpdateUser(1);
        } else {
            entity.setCreateTime(DateUtils.now());
            entity.setCreateUser(1);
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(ReviewerStrategy entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}