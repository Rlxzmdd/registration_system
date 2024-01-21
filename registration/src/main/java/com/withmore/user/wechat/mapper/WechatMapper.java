// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.user.student.vo.student.UserSimpleVo;
import com.withmore.user.wechat.entity.Wechat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 微信小程序用户表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-18
 */
@DS("registration")
@Mapper
public interface WechatMapper extends BaseMapper<Wechat> {
    /**
     * 获取指定用户的简略信息
     * @param number 学号、工号
     * @return
     */
    List<UserSimpleVo> getUserSimpleByNumber(@Param("number") String number);
}
