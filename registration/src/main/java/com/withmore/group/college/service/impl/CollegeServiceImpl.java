// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.college.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.group.college.constant.CollegeConstant;
import com.withmore.group.college.entity.College;
import com.withmore.group.college.mapper.CollegeMapper;
import com.withmore.group.college.query.CollegeQuery;
import com.withmore.group.college.service.ICollegeService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.group.college.vo.college.CollegeInfoVo;
import com.withmore.group.college.vo.college.CollegeListVo;
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
  * 学院信息表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-07-20
  */
@Service
public class CollegeServiceImpl extends BaseServiceImpl<CollegeMapper, College> implements ICollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        CollegeQuery collegeQuery = (CollegeQuery) query;
        // 查询条件
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<College> page = new Page<>(collegeQuery.getPage(), collegeQuery.getLimit());
        IPage<College> pageData = collegeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            CollegeListVo collegeListVo = Convert.convert(CollegeListVo.class, x);
            return collegeListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     */
    @Override
    public Object getInfo(Serializable id) {
        College entity = (College) super.getInfo(id);
        // 返回视图Vo
        CollegeInfoVo collegeInfoVo = new CollegeInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, collegeInfoVo);
        return collegeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(College entity) {
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
     */
    @Override
    public JsonResult delete(College entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}