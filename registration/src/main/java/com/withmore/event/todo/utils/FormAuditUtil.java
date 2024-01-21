package com.withmore.event.todo.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.todo.constant.ReviewerStrategyConstant;
import com.withmore.event.todo.dto.FormAuditPushParamDto;
import com.withmore.event.todo.entity.*;
import com.withmore.event.todo.mapper.*;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.mapper.StudentMapper;
import com.withmore.user.student.vo.student.StudentSimpleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 表单审核工具
 * 用于FormService 之间创建审核通知
 */
@Component
public class FormAuditUtil {

    private static FormReviewerMapper formReviewerMapper;

    private static FormAuditNoticeMapper formAuditNoticeMapper;

    private static FormItemMapper formItemMapper;

    private static ReviewerStrategyMapper reviewerStrategyMapper;

    private static StudentMapper studentMapper;

    @Autowired
    public void setFormReviewerMapper(FormReviewerMapper reviewerMapper) {
        FormAuditUtil.formReviewerMapper = reviewerMapper;
    }

    @Autowired
    public void setFormAuditNoticeMapper(FormAuditNoticeMapper formAuditNoticeMapper) {
        FormAuditUtil.formAuditNoticeMapper = formAuditNoticeMapper;
    }

    @Autowired
    public void setFormItemMapper(FormItemMapper formItemMapper) {
        FormAuditUtil.formItemMapper = formItemMapper;
    }

    @Autowired
    public void setReviewerStrategyMapper(ReviewerStrategyMapper reviewerStrategyMapper) {
        FormAuditUtil.reviewerStrategyMapper = reviewerStrategyMapper;
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        FormAuditUtil.studentMapper = studentMapper;
    }

    /**
     * 自动根据表单审核策略以及审核流对审核人发起审核通知
     * 首次推送表单审核情况需 increaseAuditStep=false
     * 驳回审核用户重新提交数据情况 increaseAuditStep=false
     * 审核通过，通知下一位审核人情况 increaseAuditStep=true
     * 审核流若为策略，应该使用策略表匹配的审核流ID
     * 审核流若为普通，则使用Form 中的reviewerID即可
     *
     * @param form              表单模版
     * @param item              表单数据
     * @param reviewerId        审核流ID
     * @param increaseAuditStep 是否在审核人选择时自增选择顺位审核人
     */
    private static void autoMatcherReviewerLaunchNotice(Form form, FormItem item, Integer reviewerId, Boolean increaseAuditStep) {
        // 获取表单审核流
        QueryWrapper<FormReviewer> wrapper = new QueryWrapper<>();
        wrapper.eq("id", reviewerId);
        wrapper.eq("mark", 1);
        FormReviewer reviewer = formReviewerMapper.selectOne(wrapper);

        Integer step = item.getAuditStep();
        if (increaseAuditStep) {
            step++;
        }

        String reviewerNumber = null;
        String reviewerType = null;

        // 找到当前步骤的审核人
        switch (step) {
            case 1:
                reviewerNumber = reviewer.getFirstNumber();
                reviewerType = reviewer.getFirstType();
                break;
            case 2:
                reviewerNumber = reviewer.getSecondNumber();
                reviewerType = reviewer.getSecondType();
                break;
            case 3:
                reviewerNumber = reviewer.getThirdNumber();
                reviewerType = reviewer.getThirdType();
                break;
            case 4:
                reviewerNumber = reviewer.getFourthNumber();
                reviewerType = reviewer.getFourthType();
                break;
            case 5:
                break;
        }

        // 没有下一位审核人，审核直接完结
        if (reviewerNumber == null && reviewerType == null) {
            item.setAuditFinish(true);
            item.setAuditPassed(true);
        } else {
            // 有下一位审核人记录，则通知下一位审核人
            FormAuditNotice formAuditNotice = new FormAuditNotice();
            formAuditNotice.setFormKey(form.getFormKey());
            formAuditNotice.setItemUuid(item.getItemUuid());
            formAuditNotice.setReviewerNumber(reviewerNumber);
            formAuditNotice.setReviewerType(reviewerType);
            formAuditNoticeMapper.insert(formAuditNotice);
            if (increaseAuditStep) {
                item.setAuditStep(item.getAuditStep() + 1);
            }
        }
        formItemMapper.updateById(item);
    }

    /**
     * 推送表单审核通知
     *
     * @param form              表单模版实体
     * @param item              表单数据UUID
     * @param increaseAuditStep 是否增加AuditStep
     */
    public static void pushFormAuditNotice(Form form, FormItem item, Boolean increaseAuditStep) {
        // TODO: 2021/8/26 若为教师提交的表单，不会发起审核通知
        // 无需审核情况
        if (!form.getAudit()) {
            return;
        }

        // 策略审核需要在策略表中找到相对应策略中的reviewerID
        if (form.getEnableStrategy()) {
            launchStrategyAuditNotice(form, item, increaseAuditStep);
        } else {
            // 默认（普通）审核情况
            autoMatcherReviewerLaunchNotice(form, item, form.getReviewerId(), increaseAuditStep);
        }
    }

    /**
     * 发起策略审核通知
     * 策略审核将忽略form中reviewer_id 指定的审核流
     * 由策略规则指定对应的审核流
     *
     * @param form 表单模版
     * @param item 表单数据
     */
    public static void launchStrategyAuditNotice(Form form, FormItem item, Boolean increaseAuditStep) {
        // 非策略情况直接退出 , 教师类型不支持策略
        if (!form.getEnableStrategy() || Constant.TOKEN_USER_TYPE_TEACHER.equalsIgnoreCase(item.getUserType())) {
            return;
        }
        String strategy = form.getStrategy();
        QueryWrapper<ReviewerStrategy> wrapper = new QueryWrapper<>();
        wrapper.eq("form_key", form.getFormKey());
        wrapper.eq("strategy", form.getStrategy());
        wrapper.eq("mark", 1);
        // 策略组
        List<ReviewerStrategy> strategies = reviewerStrategyMapper.selectList(wrapper);
        // 获取提交用户的信息
        StudentSimpleVo studentSimple = studentMapper.getStudentSimple(item.getSubmitNumber());
        // TODO: 2021/8/26 策略需禁止教师用户提交，教师不支持班级关键字策略
        String userStrategyTarget;
        if (ReviewerStrategyConstant.CLASS_STRATEGY.equalsIgnoreCase(strategy)) {
            userStrategyTarget = studentSimple.getClasses();
        } else if (ReviewerStrategyConstant.MAJOR_STRATEGY.equalsIgnoreCase(strategy)) {
            userStrategyTarget = studentSimple.getMajor();
        } else if (ReviewerStrategyConstant.SINGLE_STRATEGY.equalsIgnoreCase(strategy)) {
            userStrategyTarget = studentSimple.getNumber();
        } else {
            // TODO: 2021/8/25 策略值类型错误
            return;
        }
        ReviewerStrategy matchStrategy = null;
        for (ReviewerStrategy reviewerStrategy :
                strategies) {
            if (reviewerStrategy.getStrategyValue().equalsIgnoreCase(userStrategyTarget)) {
                matchStrategy = reviewerStrategy;
                break;
            }
        }
        // TODO: 2021/8/25 策略匹配失败
        if (matchStrategy == null) {
            return;
        }

        // 自动选择审核人，并对其发起审核通知
        autoMatcherReviewerLaunchNotice(form, item, matchStrategy.getReviewerId(), increaseAuditStep);
    }
}
