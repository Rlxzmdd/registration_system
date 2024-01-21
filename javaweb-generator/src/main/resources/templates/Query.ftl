// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package ${packageName}.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * ${tableAnnotation}查询条件
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
public class ${entityName}Query extends BaseQuery {

<#if model_column?exists>
    <#list model_column as model>
    <#if model.columnName = 'name'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'title'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'mobile'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'type'>
    /**
     * ${model.columnComment!}
     */
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'status'>
    /**
     * ${model.columnComment!}
     */
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    </#list>
</#if>
}
