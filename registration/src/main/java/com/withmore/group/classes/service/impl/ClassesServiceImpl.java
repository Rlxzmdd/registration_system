// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.classes.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.group.classes.dto.DictDto;
import com.withmore.group.classes.entity.Classes;
import com.withmore.group.classes.mapper.ClassesMapper;
import com.withmore.group.classes.query.ClassesQuery;
import com.withmore.group.classes.service.IClassesService;
import com.withmore.group.classes.vo.classes.ClassesContactVo;
import com.withmore.group.classes.vo.classes.ClassesInfoVo;
import com.withmore.group.classes.vo.classes.ClassesListVo;
import com.withmore.group.classes.vo.classes.ClassesSimpleVo;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import com.withmore.user.permission.utils.PermissionJudge;
import com.withmore.user.student.mapper.StudentPermissionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级信息表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class ClassesServiceImpl extends BaseServiceImpl<ClassesMapper, Classes> implements IClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private StudentPermissionMapper studentPermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ClassesQuery classesQuery = (ClassesQuery) query;
        // 查询条件
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Classes> page = new Page<>(classesQuery.getPage(), classesQuery.getLimit());
        IPage<Classes> pageData = classesMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ClassesListVo classesListVo = Convert.convert(ClassesListVo.class, x);
            return classesListVo;
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
        Classes entity = (Classes) super.getInfo(id);
        // 返回视图Vo
        ClassesInfoVo classesInfoVo = new ClassesInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, classesInfoVo);
        return classesInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(Classes entity) {
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
    public JsonResult delete(Classes entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        if (PermissionJudge.judgeClassList(nodes)) {
            IPage<ClassesSimpleVo> list = classesMapper.list(new Page<>(baseQuery.getPage(), baseQuery.getLimit()), nodes);
            return JsonResultS.success(list);
        }
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0312);
    }

    @Override
    public JsonResultS simple(Integer classesId, AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        if (PermissionJudge.judgeClassList(nodes)) {
            ClassesSimpleVo simpleVo = classesMapper.simple(classesId, nodes);
            if (simpleVo == null) {
                return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0313);
            }
            return JsonResultS.success(simpleVo);
        }
        return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0312);
    }

    @Override
    public JsonResultS contact(String id) {
        List<ClassesContactVo> contactVos = classesMapper.contact(id);
        return JsonResultS.success(contactVos);
    }

    @Override
    public JsonResultS dict() {
        List<DictDto> list = classesMapper.dict();
        Map<String, Map<String, ArrayList<String>>> dict = new HashMap<>();
        for (DictDto dto : list) {
            if (!dict.containsKey(dto.getCollege())) {
                dict.put(dto.getCollege(), new HashMap<>());
            }
            Map<String, ArrayList<String>> college = dict.get(dto.getCollege());
            if (!college.containsKey(dto.getMajor())) {
                college.put(dto.getMajor(), new ArrayList<>());
            }
            ArrayList<String> classes = college.get(dto.getMajor());
            classes.add(dto.getClasses());
        }
        return JsonResultS.success(dict);
    }
}