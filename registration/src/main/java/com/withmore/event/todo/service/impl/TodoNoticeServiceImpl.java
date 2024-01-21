package com.withmore.event.todo.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.PageUtil;
import com.withmore.event.todo.dto.NoticePushDto;
import com.withmore.event.todo.dto.NoticeDetailsDto;
import com.withmore.event.todo.entity.TodoNotice;
import com.withmore.event.todo.entity.TodoNoticeImg;
import com.withmore.event.todo.entity.TodoNoticePermission;
import com.withmore.event.todo.mapper.TodoNoticeImgMapper;
import com.withmore.event.todo.mapper.TodoNoticeMapper;
import com.withmore.event.todo.mapper.TodoNoticePermissionMapper;
import com.withmore.event.todo.query.TodoNoticeQuery;
import com.withmore.event.todo.service.ITodoNoticeService;
import com.withmore.event.todo.vo.todonotice.TodoNoticeDetailsVo;
import com.withmore.event.todo.vo.todonotice.TodoNoticeInfoVo;
import com.withmore.event.todo.vo.todonotice.TodoNoticeListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 待办事项-通知信息表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Service
public class TodoNoticeServiceImpl extends BaseServiceImpl<TodoNoticeMapper, TodoNotice> implements ITodoNoticeService {

    @Autowired
    private TodoNoticeMapper todoNoticeMapper;

    @Autowired
    private TodoNoticeImgMapper todoNoticeImgMapper;

    @Autowired
    private TodoNoticePermissionMapper todoNoticePermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        TodoNoticeQuery todoNoticeQuery = (TodoNoticeQuery) query;
        // 查询条件
        QueryWrapper<TodoNotice> queryWrapper = new QueryWrapper<>();
        // 代办事项标题
        if (!StringUtils.isEmpty(todoNoticeQuery.getTitle())) {
            queryWrapper.like("title", todoNoticeQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<TodoNotice> page = new Page<>(todoNoticeQuery.getPage(), todoNoticeQuery.getLimit());
        IPage<TodoNotice> pageData = todoNoticeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            TodoNoticeListVo todoNoticeListVo = Convert.convert(TodoNoticeListVo.class, x);
            return todoNoticeListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        TodoNotice entity = (TodoNotice) super.getInfo(id);
        // 返回视图Vo
        TodoNoticeInfoVo todoNoticeInfoVo = new TodoNoticeInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, todoNoticeInfoVo);
        return todoNoticeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(TodoNotice entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
            entity.setUpdateUser(1);
        } else {
            entity.setCreateTime(DateUtils.now());
            entity.setCreateUser(1);
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(TodoNotice entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 判断是否存在记录
     *
     * @param title 通知标题
     * @return
     */
    private boolean isExists(String title, String initiatorNumber, String initiatorType) {
        QueryWrapper<TodoNotice> todoNoticeQueryWrapper = new QueryWrapper<>();
        todoNoticeQueryWrapper.eq("title", title);
        todoNoticeQueryWrapper.eq("initiator_number", initiatorNumber);
        todoNoticeQueryWrapper.eq("initiator_type", initiatorType);
        todoNoticeQueryWrapper.eq("mark", 1);
        return todoNoticeMapper.selectOne(todoNoticeQueryWrapper) != null;
    }

    @Override
    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public JsonResultS release(NoticePushDto notice, AuthToken2CredentialDto dto) {
        notice.setInitiatorNumber(dto.getNumber()).setInitiatorType(dto.getType());
        // 重复条件-> 标题+发布人及其类型
        if (isExists(notice.getTitle(), notice.getInitiatorNumber(), notice.getInitiatorType())) {
            return JsonResultS.error(ResultCodeEnum.NOTICE_REPEAT);
        }
        todoNoticeMapper.insert((TodoNotice) notice);
        // 创建公告图片关联
        for (String uuid :
                notice.getImages()) {
            todoNoticeImgMapper.insert(new TodoNoticeImg().setNoticeId(notice.getId()).setUuid(uuid));
        }
        // 创建权限节点关联
        for (Integer permissionId :
                notice.getPermissionId()) {
            todoNoticePermissionMapper.insert(
                    new TodoNoticePermission().setNoticeId(notice.getId()).setPermissionId(permissionId));
        }
        return JsonResultS.success();
    }

    /**
     * 过滤并去重转换 TodoNoticeDetailsDto -> TodoNoticeDetailsVo
     *
     * @param detailsList 通知详情列表
     * @param baseQuery   分页参数
     * @return
     */
    private IPage<TodoNoticeDetailsVo> filterDetails(List<NoticeDetailsDto> detailsList, BaseQuery baseQuery) {
        Map<Integer, TodoNoticeDetailsVo> filter = new HashMap<>();
        for (NoticeDetailsDto dto :
                detailsList) {
            if (!filter.containsKey(dto.getId())) {
                filter.put(dto.getId(),
                        TodoNoticeDetailsVo.builder()
                                .title(dto.getTitle())
                                .content(dto.getContent())
                                .publisher(dto.getPublisher())
                                .releaseTime(dto.getReleaseTime())
                                .images(new ArrayList<>())
                                .id(dto.getId())
                                .build()
                );
            }
            filter.get(dto.getId())
                    .getImages()
                    .add(dto.getUrl());
        }
        List<TodoNoticeDetailsVo> values = new ArrayList<>(filter.values());
        values.sort(new Comparator<TodoNoticeDetailsVo>() {
            @Override
            public int compare(TodoNoticeDetailsVo o1, TodoNoticeDetailsVo o2) {
                if (o1.getReleaseTime().before(o2.getReleaseTime())) {
                    return 1;
                } else if (o1.getReleaseTime().equals(o2.getReleaseTime())) {
                    return 0;
                }
                return -1;
            }
        });
        return PageUtil.getPages(baseQuery, values);
    }

    @Override
    public JsonResultS list(BaseQuery baseQuery, AuthToken2CredentialDto dto) {
        PermissionNode node = PermissionConvert.convert2Node(dto);
        if (node == null) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0305);
        }
        List<NoticeDetailsDto> detailsList = todoNoticeMapper.getTodoNoticeDetailsList(node, Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        IPage<TodoNoticeDetailsVo> details = filterDetails(detailsList, baseQuery);

        return JsonResultS.success(details);
    }
}