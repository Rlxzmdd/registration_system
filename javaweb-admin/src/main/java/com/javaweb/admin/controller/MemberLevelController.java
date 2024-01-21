// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.admin.controller;


import com.javaweb.admin.entity.MemberLevel;
import com.javaweb.admin.query.MemberLevelQuery;
import com.javaweb.admin.service.IMemberLevelService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.system.common.BaseController;

/**
 * <p>
 * 会员级别表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/memberlevel")
public class MemberLevelController extends BaseController {

    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 获取会员等级列表
     *
     * @param memberLevelQuery 查询条件
     * @return
     */
    @RequiresPermissions("sys:memberlevel:index")
    @GetMapping("/index")
    public JsonResult index(MemberLevelQuery memberLevelQuery) {
        return memberLevelService.getList(memberLevelQuery);
    }

    /**
     * 添加会员等级
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员等级", logType = LogType.INSERT)
    @RequiresPermissions("sys:memberlevel:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody MemberLevel entity) {
        return memberLevelService.edit(entity);
    }

    /**
     * 编辑会员等级
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员等级", logType = LogType.UPDATE)
    @RequiresPermissions("sys:memberlevel:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody MemberLevel entity) {
        return memberLevelService.edit(entity);
    }

    /**
     * 删除会员等级
     *
     * @param memberLevelIds 会员ID
     * @return
     */
    @Log(title = "会员等级", logType = LogType.DELETE)
    @RequiresPermissions("sys:memberlevel:delete")
    @DeleteMapping("/delete/{memberLevelIds}")
    public JsonResult delete(@PathVariable("memberLevelIds") Integer[] memberLevelIds) {
        return memberLevelService.deleteByIds(memberLevelIds);
    }

    /**
     * 获取会员等级列表
     *
     * @return
     */
    @GetMapping("/getMemberLevelList")
    public JsonResult getMemberLevelList() {
        return memberLevelService.getMemberLevelList();
    }

}
