// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package ${packageName}.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.system.common.BaseController;
import ${packageName}.entity.${entityName};
import ${packageName}.query.${entityName}Query;
import ${packageName}.service.I${entityName}Service;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * ${tableAnnotation} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${entityName?lower_case}")
public class ${entityName}Controller extends BaseController {

    @Autowired
    private I${entityName}Service ${entityName?uncap_first}Service;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:${entityName?lower_case}:index")
    @GetMapping("/index")
    public JsonResult index(${entityName}Query query) {
        return ${entityName?uncap_first}Service.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "${tableAnnotation}", logType = LogType.INSERT)
    @RequiresPermissions("sys:${entityName?lower_case}:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param ${entityName?lower_case}Id 记录ID
     * @return
     */
    @GetMapping("/info/{${entityName?lower_case}Id}")
    public JsonResult info(@PathVariable("${entityName?lower_case}Id") Integer ${entityName?lower_case}Id) {
        return ${entityName?uncap_first}Service.info(${entityName?lower_case}Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "${tableAnnotation}", logType = LogType.UPDATE)
    @RequiresPermissions("sys:${entityName?lower_case}:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param ${entityName?lower_case}Ids 记录ID
     * @return
     */
    @Log(title = "${tableAnnotation}", logType = LogType.DELETE)
    @RequiresPermissions("sys:${entityName?lower_case}:drop")
    @DeleteMapping("/delete/{${entityName?lower_case}Ids}")
    public JsonResult delete(@PathVariable("${entityName?lower_case}Ids") Integer[] ${entityName?lower_case}Ids) {
        return ${entityName?uncap_first}Service.deleteByIds(${entityName?lower_case}Ids);
    }

<#if model_column?exists>
    <#list model_column as model>
        <#if model.columnSwitch == true>
    /**
     * 设置${model.columnCommentName}
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "${tableAnnotation}", logType = LogType.STATUS)
    @RequiresPermissions("sys:${entityName?lower_case}:${model.columnName}")
    @PutMapping("/set${model.changeColumnName?cap_first}")
    public JsonResult set${model.changeColumnName?cap_first}(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.set${model.changeColumnName?cap_first}(entity);
    }
        </#if>
    </#list>
</#if>
}