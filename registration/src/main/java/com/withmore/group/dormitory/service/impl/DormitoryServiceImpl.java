// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.dormitory.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.group.dormitory.entity.Dormitory;
import com.withmore.group.dormitory.mapper.DormitoryMapper;
import com.withmore.group.dormitory.query.DormitoryQuery;
import com.withmore.group.dormitory.service.IDormitoryService;
import com.withmore.group.dormitory.vo.dormitory.DormitoryInfoVo;
import com.withmore.group.dormitory.vo.dormitory.DormitoryListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
  * <p>
  * 宿舍信息表 服务类实现
  * </p>
  *
  * @author Cheney
  * @since 2021-09-11
  */
@Service
public class DormitoryServiceImpl extends BaseServiceImpl<DormitoryMapper, Dormitory> implements IDormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DormitoryQuery dormitoryQuery = (DormitoryQuery) query;
        // 查询条件
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Dormitory> page = new Page<>(dormitoryQuery.getPage(), dormitoryQuery.getLimit());
        IPage<Dormitory> pageData = dormitoryMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            DormitoryListVo dormitoryListVo = Convert.convert(DormitoryListVo.class, x);
            return dormitoryListVo;
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
        Dormitory entity = (Dormitory) super.getInfo(id);
        // 返回视图Vo
        DormitoryInfoVo dormitoryInfoVo = new DormitoryInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, dormitoryInfoVo);
        return dormitoryInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(Dormitory entity) {
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
    public JsonResult delete(Dormitory entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

}