// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.generator.dto.GenTableDto;
import com.javaweb.generator.entity.GenTable;
import com.javaweb.generator.entity.GenTableColumn;
import com.javaweb.generator.query.GenTableQuery;
import com.javaweb.generator.service.IGenTableColumnService;
import com.javaweb.generator.service.IGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/gentable")
public class GenTableController {

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 获取业务表列表
     *
     * @param genTableQuery 查询条件
     */
    @GetMapping("/index")
    public JsonResult index(GenTableQuery genTableQuery) {
        return genTableService.getList(genTableQuery);
    }

    /**
     * 获取数据库表
     *
     * @param query 查询条件
     */
    @GetMapping("/genDbTableList")
    public JsonResult genDbTableList(GenTableQuery query) {
        IPage<GenTable> pageData = genTableService.genDbTableList(query);
        return JsonResult.success(pageData, "操作成功");
    }

    /**
     * 导入表
     *
     * @param tableNames 数据表
     */
    @PostMapping("/importTable")
    public JsonResult importTable(@RequestBody String[] tableNames) {
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return JsonResult.success();
    }

    /**
     * 获取表详情信息
     *
     * @param tableId 表ID
     */
    @GetMapping("/getTableInfo/{tableId}")
    public JsonResult getTableInfo(@PathVariable("tableId") String tableId) {
        GenTable table = genTableService.selectGenTableById(Integer.valueOf(tableId));
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(Integer.valueOf(tableId));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("records", list);
        return JsonResult.success(map, "操作成功");
    }

    /**
     * 更新代码生成表信息
     *
     * @param genTable 生成表
     */
    @PutMapping("/updateGenTable")
    public JsonResult updateGenTable(@Validated @RequestBody GenTable genTable) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return JsonResult.success();
    }

    /**
     * 删除业务表
     *
     * @param tableIds 业务表ID
     */
    @DeleteMapping("/delete/{tableIds}")
    public JsonResult delete(@PathVariable("tableIds") Integer[] tableIds) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        return genTableService.delete(tableIds);
    }

    /**
     * 生成代码
     *
     * @param genTableDto 一键生成参数
     * @throws Exception
     */
    @PostMapping("/batchGenCode")
    public JsonResult batchGenCode(@RequestBody GenTableDto genTableDto) {
        if (CommonConfig.appDebug) {
            return JsonResult.error("演示环境禁止操作");
        }
        String[] tableNames = genTableDto.getTableNames().split(",");
        return genTableService.generatorCode(tableNames);
    }

}
