// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmore.event.form.constan.FormAuditNoticeStatus;
import com.withmore.event.form.dto.FormAuditNoticeTranDto;
import com.withmore.event.form.entity.FormAuditNotice;
import com.withmore.event.form.vo.formAuditNotice.FormAuditNoticeSimpleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 审核通知表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-08-07
 */
@Mapper
@DS("registration")
public interface FormAuditNoticeMapper extends BaseMapper<FormAuditNotice> {

    /**
     * 获取审核通知列表
     *
     * @param limit          每个表单类型的条数
     * @param status         审核状态
     * @param reviewerNumber 审核人学号、工号
     * @param reviewerType   审核人类型
     */
    List<FormAuditNoticeSimpleVo> getAuditNotices(@Param("limit") Integer limit,
                                                  @Param("status") FormAuditNoticeStatus status,
                                                  @Param("number") String reviewerNumber,
                                                  @Param("type") String reviewerType);

    /**
     * 分页查询指定表单模版的审核通知列表
     *
     * @param page           分页参数
     * @param formKey        模版key
     * @param status         通知所属状态
     * @param reviewerNumber 审核人学号，工号
     * @param reviewerType   审核人类型
     */
    IPage<FormAuditNoticeSimpleVo> getAuditNoticeByFormKey(Page<FormAuditNoticeSimpleVo> page,
                                                           @Param("formKey") String formKey,
                                                           @Param("status") FormAuditNoticeStatus status,
                                                           @Param("number") String reviewerNumber,
                                                           @Param("type") String reviewerType);

    /**
     * 查询指定的审核通知记录
     * 根据表单类型key和提交数据UUID
     * 记录筛选为最后创建的通知记录那一条
     *
     * @param formKey        模版key
     * @param uuid           表单数据UUID
     * @param reviewerNumber 审核人学号，工号
     * @param reviewerType   审核人类型
     */
    FormAuditNoticeTranDto getAuditNoticeByFormKeyAndUuid(@Param("formKey") String formKey,
                                                          @Param("uuid") String uuid,
                                                          @Param("reviewerNumber") String reviewerNumber,
                                                          @Param("reviewerType") String reviewerType);

    /**
     * 获取最后一条指定的表单数据的审核通知
     *
     * @param itemUuid 表单数据UUID
     */
    FormAuditNotice getLastFormAuditNotice(@Param("uuid") String itemUuid);
}
