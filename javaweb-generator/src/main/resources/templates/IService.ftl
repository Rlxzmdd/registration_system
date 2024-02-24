// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package ${packageName}.service;

import com.javaweb.common.utils.JsonResult;
import ${packageName}.entity.${entityName};
import com.javaweb.system.common.IBaseService;

/**
 * <p>
 * ${tableAnnotation} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface I${entityName}Service extends IBaseService<${entityName}> {

<#if model_column?exists>
 <#list model_column as model>
  <#if model.columnSwitch == true && model.changeColumnName?uncap_first != 'status'>
    /**
     * 设置${model.columnCommentName}
     *
     * @param entity 实体对象
     */
    JsonResult set${model.changeColumnName?cap_first}(${entityName} entity);

  </#if>
 </#list>
</#if>

}