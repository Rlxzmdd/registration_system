// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.user.wechat.entity.Mapper;
import com.withmore.user.wechat.query.MapperQuery;
import com.withmore.user.wechat.service.IMapperService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小程序kv映射表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-24
 */
@RestController
@RequestMapping("/mapper")
public class MapperController extends BaseController {

    @Autowired
    private IMapperService mapperService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:mapper:index")
    @GetMapping("/index")
    public JsonResult index(MapperQuery query) {
        return mapperService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "小程序kv映射表", logType = LogType.INSERT)
    @RequiresPermissions("sys:mapper:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Mapper entity) {
        return mapperService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param mapperId 记录ID
     * @return
     */
    @GetMapping("/info/{mapperId}")
    public JsonResult info(@PathVariable("mapperId") Integer mapperId) {
        return mapperService.info(mapperId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "小程序kv映射表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:mapper:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Mapper entity) {
        return mapperService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param mapperIds 记录ID
     * @return
     */
    @Log(title = "小程序kv映射表", logType = LogType.DELETE)
    @RequiresPermissions("sys:mapper:drop")
    @DeleteMapping("/delete/{mapperIds}")
    public JsonResult delete(@PathVariable("mapperIds") Integer[] mapperIds) {
        return mapperService.deleteByIds(mapperIds);
    }

    /**
     * 获取KV字典
     *
     * @param token 用户Token
     * @return
     */
    @GetMapping("/query/mapper")
    public JsonResultS simple(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return mapperService.simple();
    }
}