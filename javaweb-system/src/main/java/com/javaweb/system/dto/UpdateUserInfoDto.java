// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.dto;

import lombok.Data;

/**
 * 更新个人资料
 */
@Data
public class UpdateUserInfoDto {

    /**
     * 个人头像
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 个人简介
     */
    private String intro;

    /**
     * 个性签名
     */
    private String signature;

}
