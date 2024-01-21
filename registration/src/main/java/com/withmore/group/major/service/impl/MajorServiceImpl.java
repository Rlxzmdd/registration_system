// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.major.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.group.major.constant.MajorConstant;
import com.withmore.group.major.entity.Major;
import com.withmore.group.major.mapper.MajorMapper;
import com.withmore.group.major.query.MajorQuery;
import com.withmore.group.major.service.IMajorService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.group.major.vo.major.MajorInfoVo;
import com.withmore.group.major.vo.major.MajorListVo;
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
  * 专业信息表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-07-20
  */
@Service
public class MajorServiceImpl extends BaseServiceImpl<MajorMapper, Major> implements IMajorService {

    @Autowired
    private MajorMapper majorMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MajorQuery majorQuery = (MajorQuery) query;
        // 查询条件
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Major> page = new Page<>(majorQuery.getPage(), majorQuery.getLimit());
        IPage<Major> pageData = majorMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            MajorListVo majorListVo = Convert.convert(MajorListVo.class, x);
            return majorListVo;
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
        Major entity = (Major) super.getInfo(id);
        // 返回视图Vo
        MajorInfoVo majorInfoVo = new MajorInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, majorInfoVo);
        return majorInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Major entity) {
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
    public JsonResult delete(Major entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}