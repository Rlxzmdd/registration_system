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

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.admin.entity.Member;
import com.javaweb.admin.mapper.MemberMapper;
import com.javaweb.admin.query.MemberQuery;
import com.javaweb.admin.service.IMemberService;
import com.javaweb.admin.vo.MemberListVo;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-04
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 获取会员等级
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MemberQuery memberQuery = (MemberQuery) query;
        // 查询条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        // 手机号码
        if (!StringUtils.isEmpty(memberQuery.getMobile())) {
            queryWrapper.like("mobile", memberQuery.getMobile());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询分页数据
        IPage<Member> page = new Page<>(memberQuery.getPage(), memberQuery.getLimit());
        IPage<Member> pageData = memberMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            MemberListVo memberListVo = Convert.convert(MemberListVo.class, x);
            // 头像
            if (!StringUtils.isEmpty(x.getAvatar())) {
                memberListVo.setAvatar(CommonUtils.getImageURL(x.getAvatar()));
            }
            // 城市数据处理
            if (StringUtils.isNotNull(x.getProvinceCode()) &&
                    StringUtils.isNotNull(x.getCityCode()) &&
                    StringUtils.isNotNull(x.getDistrictCode())) {
                // 初始化数组
                String[] strings = new String[3];
                strings[0] = x.getProvinceCode();
                strings[1] = x.getCityCode();
                strings[2] = x.getDistrictCode();
                memberListVo.setCity(strings);
            }
            return memberListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑用户
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Member entity) {
        // 头像处理
        if (!StringUtils.isEmpty(entity.getAvatar()) && entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        // 省市区处理
        if (entity.getCity().length != 3) {
            return JsonResult.error("请选择省市区");
        }
        // 省份
        entity.setProvinceCode(entity.getCity()[0]);
        // 城市
        entity.setCityCode(entity.getCity()[1]);
        // 省份
        entity.setDistrictCode(entity.getCity()[2]);
        return super.edit(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(Member entity) {
        Member member = new Member();
        member.setId(entity.getId());
        member.setStatus(entity.getStatus());
        return super.setStatus(member);
    }

}
