// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.role.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.role.entity.WechatMiniProgramRole;
import com.withmore.user.role.mapper.WechatMiniProgramRoleMapper;
import com.withmore.user.role.query.RoleQuery;
import com.withmore.user.role.service.IWechatMiniProgramRoleService;
import com.withmore.user.role.vo.role.RoleInfoVo;
import com.withmore.user.role.vo.role.RoleListVo;
import com.javaweb.common.utils.JsonResult;
import com.withmore.user.role.vo.role.RoleSimpleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户权限表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-24
 */
@Service
public class WechatMiniProgramRoleServiceImpl extends BaseServiceImpl<WechatMiniProgramRoleMapper, WechatMiniProgramRole> implements IWechatMiniProgramRoleService {

    @Autowired
    private WechatMiniProgramRoleMapper wechatMiniProgramRoleMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        RoleQuery roleQuery = (RoleQuery) query;
        // 查询条件
        QueryWrapper<WechatMiniProgramRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<WechatMiniProgramRole> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        IPage<WechatMiniProgramRole> pageData = wechatMiniProgramRoleMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            RoleListVo roleListVo = Convert.convert(RoleListVo.class, x);
            return roleListVo;
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
        WechatMiniProgramRole entity = (WechatMiniProgramRole) super.getInfo(id);
        // 返回视图Vo
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, roleInfoVo);
        return roleInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(WechatMiniProgramRole entity) {
        if (entity.getId() != null && entity.getId() > 0) {
        } else {
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
    public JsonResult delete(WechatMiniProgramRole entity) {
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS role(AuthToken2CredentialDto dto) {
        List<RoleSimpleVo> simpleVos = wechatMiniProgramRoleMapper.role(dto.getNumber());
        return JsonResultS.success(simpleVos);
    }
}