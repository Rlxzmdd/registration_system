DROP DATABASE IF EXISTS `registration_system`;
Create DATABASE `registration_system` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
use `registration_system`;
# 用户信息
create table permission_node
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    level1      varchar(15)                                not null comment '一级权限',
    level2      varchar(15)                                not null comment '二级权限',
    level3      varchar(15)                                not null comment '三级权限',
    level4      varchar(15)                                not null comment '四级权限',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建用户',
    update_user int unsigned                               null comment '更新用户',
    mark        tinyint unsigned default '1'               null comment '记录有效期性'
)
    comment '报到系统模块权限表';

create index idx_id
    on permission_node (id);

create table resource_img
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    uuid          varchar(40)                                not null comment '图片UUID',
    md5           varchar(50)                                null comment '图片md5',
    url           varchar(500)                               not null comment 'OSS链接',
    create_number varchar(15)                                not null comment '上传者工号/学号',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_uuid
    on resource_img (uuid);

create table user_role
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    user_number varchar(15)                                not null comment '用户学号/工号',
    user_type   varchar(15)                                not null comment '用户类型',
    role_id     int unsigned                               not null comment '角色ID',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned     default '0'               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '用户权限表';

create index idx_user_number
    on user_role (user_number);

create table user_student
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    real_name     varchar(30)                                null comment '学生姓名',
    stu_number    varchar(15)                                not null comment '学生学号',
    phone         varchar(11)                                null comment '手机号',
    id_card       varchar(20)                                null comment '身份证号',
    gender        varchar(3)                                 null comment '性别',
    nation        varchar(10)                                null comment '民族',
    email         varchar(30)                                null comment '邮箱',
    qq            varchar(30)                                null comment 'QQ号',
    politic       varchar(8)                                 null comment '政治面貌',
    serial_number varchar(30)                                null comment '通知书编号',
    exam_number   varchar(30)                                null comment '考生号',
    stu_status    varchar(10)                                null comment '学生状态',
    college_id    bigint unsigned  default '0'               null comment '所属学院索引',
    major_id      bigint unsigned  default '0'               null comment '所属专业索引',
    classes_id    bigint unsigned                            null comment '所属班级索引',
    dorm_id       bigint unsigned                            null comment '所属宿舍索引',
    wx_id         bigint unsigned                            null comment '微信表索引',
    avatar        varchar(40)                                null comment '学生头像照片',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '记录创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '记录更新时间',
    create_user   int unsigned                               null comment '创建记录用户',
    update_user   int unsigned                               null comment '更新记录用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '学生信息表';

create index idx_stu_number
    on user_student (stu_number);

create table user_student_label
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    stu_number  varchar(15)                                not null comment '学生学号',
    label       varchar(15)                                not null comment '标签内容',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '学生标签表';

create table user_student_permission
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    stu_number    varchar(15)                                not null comment '学生学号',
    permission_id bigint unsigned                            not null comment '权限节点索引',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '学生可访问权限';

create index idx_stu_number
    on user_student_permission (stu_number);

create table user_student_remark
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    stu_number  varchar(15)                                not null comment '学生学号',
    tch_number  varchar(15)                                not null comment '教师工号',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    remark      text                                       null comment '备注内容',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '学生备注表';

create index idx_stu_number
    on user_student_remark (stu_number);

create index idx_tch_number
    on user_student_remark (tch_number);

create table user_teacher
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    real_name     varchar(30)                                null comment '教师姓名',
    tch_number    varchar(15)                                not null comment '教师工号',
    gender        varchar(3)                                 null comment '性别',
    phone         varchar(11)                                null comment '联系电话',
    email         varchar(30)                                null comment '邮箱',
    qq            varchar(30)                                null comment 'QQ号',
    office        varchar(100)                               null comment '办公室位置',
    password      varchar(50)                                null comment '登录密码',
    identity      varchar(10)      default '教师'            null comment '教师其他职责身份',
    college_id    bigint unsigned  default '0'               null comment '所属学院索引',
    group_id      bigint unsigned  default '0'               null comment '所属组ID',
    department_id bigint unsigned  default '0'               null comment '部门ID',
    wx_id         bigint unsigned                            null comment '微信表索引',
    create_user   int unsigned                               null comment '创建记录用户',
    update_user   int unsigned                               null comment '更新记录用户',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_tch_number
    on user_teacher (tch_number);

create table user_teacher_permission
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    tch_number    varchar(15)                                not null comment '教师工号',
    permission_id bigint unsigned                            not null comment '权限节点索引',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '教师可访问权限';

create index idx_tch_number
    on user_teacher_permission (tch_number);

create table user_wechat
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    open_id     varchar(200)                               not null comment '微信小程序用户ID',
    union_id    varchar(200)                               null comment '微信用户识别ID',
    session_key varchar(200)                               null comment '微信小程序用户Key',
    wechat_name varchar(50)                                null comment '微信帐号',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间'
)
    comment '用户微信表';

create index idx_open_id
    on user_wechat (open_id);

create table wechat_mapper
(
    mark         tinyint unsigned default '1'               null comment '记录有效性',
    update_user  int unsigned                               null comment '更新用户',
    create_user  int unsigned                               null comment '创建用户',
    update_time  datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_time  datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    mapper_value varchar(15)                                not null comment '值',
    mapper_key   varchar(50)                                not null comment '键',
    id           bigint unsigned auto_increment comment '记录ID'
        primary key
);

create table wechat_route
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    role_id     bigint unsigned                            not null comment '角色ID',
    route_name  varchar(50)                                not null comment '路由名称',
    icon        varchar(50)                                null comment '图标名',
    type        varchar(15)                                not null comment '页面类型',
    url_name    varchar(50)                                not null comment '路由地址',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建用户',
    update_user int unsigned                               null comment '更新用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
);

# 组织信息

create table entity_college
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '记录更新时间',
    coll_name   varchar(30)                                not null comment '学院全称',
    coll_abbr   varchar(30)                                not null comment '学院简称',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '学院信息表';

create index idx_id
    on entity_college (id);

create table entity_department
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '记录更新时间',
    dep_name    varchar(30)                                not null comment '部门全称',
    dep_abbr    varchar(30)                                not null comment '部门简称',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '部门信息表';

create index idx_id
    on entity_department (id);

create table entity_dormitory
(
    id             bigint unsigned auto_increment comment '记录ID'
        primary key,
    max            int unsigned                               null comment '宿舍人数',
    campus         varchar(15)                                null comment '所在校区',
    block          varchar(15)                                null comment '所属片区',
    cell           varchar(15)                                null comment '楼栋',
    floor          int unsigned                               null comment '楼层',
    room           varchar(15)                                null comment '房号',
    abbr           varchar(15)                                null comment '房间简称',
    manager_number varchar(15)                                null comment '宿舍长学号',
    create_time    datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user    int unsigned     default '0'               null comment '创建记录用户',
    update_user    int unsigned                               null comment '更新记录用户',
    mark           tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '宿舍信息表';
create index idx_id
    on entity_dormitory (id);

create table entity_major
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    maj_name    varchar(30)                                not null comment '专业全称',
    maj_abbr    varchar(30)                                not null comment '专业简称',
    college_id  bigint unsigned  default '0'               null comment '专业所属学院',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '专业信息表';
create index idx_id
    on entity_major (id);

create table entity_classes
(
    id               bigint unsigned auto_increment comment '记录ID'
        primary key,
    class_name       varchar(30)                                not null comment '班级名称',
    teacher_number   varchar(15)                                null comment '班主任工号',
    counselor_number varchar(15)                                null comment '辅导员工号',
    assistant_number varchar(15)                                null comment '助辅学号',
    college_id       bigint unsigned  default '0'               null comment '所属学院索引',
    major_id         bigint unsigned  default '0'               null comment '所属专业索引',
    create_time      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user      int unsigned                               null comment '创建记录用户',
    update_user      int unsigned                               null comment '更新记录用户',
    mark             tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '班级信息表';
create index idx_id
    on entity_classes (id);

# 活动信息
create table activity_examine
(
    id               bigint unsigned auto_increment
        primary key,
    activity_item_id int unsigned                               not null,
    examined_number  varchar(15)                                not null,
    examined_type    varchar(15)                                not null,
    examiner_number  varchar(15)                                not null,
    examiner_type    varchar(15)                                not null,
    examine_time     datetime         default CURRENT_TIMESTAMP not null,
    serial_num       int unsigned     default '1'               not null,
    create_time      datetime         default CURRENT_TIMESTAMP null,
    create_user      int unsigned                               null,
    update_time      datetime         default CURRENT_TIMESTAMP null,
    update_user      int unsigned                               null,
    mark             tinyint unsigned default '1'               null
)
    comment '活动核销表';

create table activity_item_info
(
    id                  bigint unsigned auto_increment
        primary key,
    title               varchar(64)                                not null comment '活动标题',
    location            varchar(16)                                null comment '活动小标题',
    creator_number      varchar(15)                                not null comment '创建活动者number',
    creator_type        varchar(15)                                not null,
    is_durative         tinyint unsigned default '0'               not null comment '是否长期开放',
    is_apply            tinyint unsigned default '0'               not null comment '是否需要报名',
    is_repeat           tinyint unsigned default '0'               not null comment '是否允许重新核销',
    is_share            tinyint unsigned default '1'               not null comment '是否允许转发',
    max_num             int unsigned     default '0'               null comment '最大人数',
    apply_time_start    datetime                                   null comment '报名开始时间',
    apply_time_end      datetime                                   null comment '报名结束时间',
    activity_time_start datetime                                   null comment '活动开始时间',
    activity_time_end   datetime                                   null comment '活动结束时间',
    create_time         datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    create_user         int                                        null,
    update_user         int                                        null,
    update_time         datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    mark                tinyint unsigned default '1'               not null comment '是否有效',
    constraint id
        unique (id)
)
    comment '单项活动的关键信息';

create table activity_item_manage_list
(
    id               int unsigned auto_increment
        primary key,
    activity_item_id int unsigned                               not null comment '活动id',
    manager_number   varchar(15)                                not null comment '用户number',
    manager_type     varchar(15)                                not null,
    inviter_number   varchar(15)                                not null comment '邀请的用户',
    inviter_type     varchar(15)                                not null,
    is_read          tinyint unsigned default '1'               not null comment '是否可以下载核销数据',
    is_modify        tinyint unsigned default '0'               not null comment '是否可修改活动信息',
    is_examine       tinyint unsigned default '1'               not null comment '是否可核销活动人员',
    is_invite        tinyint unsigned default '0'               not null comment '是否可邀请其他用户成为管理',
    invite_time      datetime         default CURRENT_TIMESTAMP null comment '邀请时间',
    create_time      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    create_user      int                                        null,
    update_time      datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    update_user      int                                        null,
    mark             tinyint unsigned default '1'               not null comment '是否有效'
)
    comment '用于记录活动的管理者(不包括创建者)';

create table activity_item_profile
(
    activity_item_id int unsigned                               not null
        primary key,
    rich_text        text                                       not null comment '富文本的活动介绍',
    create_time      datetime         default CURRENT_TIMESTAMP null,
    create_user      int                                        null,
    update_time      datetime         default CURRENT_TIMESTAMP null,
    update_user      int                                        null,
    mark             tinyint unsigned default '1'               null comment '是否有效'
);

create table activity_sign_up
(
    id               int unsigned auto_increment
        primary key,
    activity_item_id int unsigned                               not null comment '活动id',
    user_number      varchar(15)                                not null comment '报名用户number',
    user_type        varchar(15)                                not null,
    sign_up_time     datetime         default CURRENT_TIMESTAMP null comment '报名时间',
    is_approval      tinyint unsigned default '0'               not null comment '报名是否通过',
    create_time      datetime         default CURRENT_TIMESTAMP null,
    create_user      int                                        null,
    update_time      datetime         default CURRENT_TIMESTAMP null,
    update_user      int                                        null,
    mark             tinyint unsigned default '1'               not null comment '是否有效'
);

# 表单信息
create table event_form
(
    id               bigint unsigned auto_increment comment '记录ID'
        primary key,
    title            varchar(50)                                not null comment '事项标题',
    initiator_number varchar(15)                                not null comment '发起人学号/工号',
    initiator_type   varchar(15)                                null comment '发起人类型',
    form_key         varchar(50)                                not null comment '表单类型key',
    template         json                                       not null comment '表单模版',
    audit            tinyint unsigned default '0'               not null comment '是否需要审核',
    reviewer_id      bigint unsigned                            null comment '审核流ID',
    start_time       datetime                                   not null comment '开始填写时间',
    end_time         datetime                                   not null comment '结束填报时间',
    enable_strategy  tinyint unsigned default '0'               null comment '是否启用审核策略',
    strategy         varchar(15)                                null comment '策略规则',
    create_time      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user      int unsigned                               null comment '创建记录用户',
    update_user      int unsigned                               null comment '更新记录用户',
    mark             tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-填报事项';

create table event_form_audit
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    form_key    varchar(15)                                not null comment '表单类型key',
    item_uuid   varchar(36)                                not null comment '提交的表单数据UUID',
    opinion     varchar(100)                               null comment '审核意见',
    is_through  tinyint unsigned default '0'               null comment '是否审核通过',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建记录用户',
    update_user int unsigned                               null comment '更新记录用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-表单审核状态表';

create table event_form_audit_notice
(
    id              bigint unsigned auto_increment comment '记录ID'
        primary key,
    form_key        varchar(15)                                not null comment '表单类型key',
    item_uuid       varchar(36)                                not null comment '提交的表单数据UUID',
    audit_id        bigint unsigned                            null comment '审核记录',
    reviewer_number varchar(15)                                not null comment '审核人工号/学号',
    reviewer_type   varchar(15)                                not null comment '审核人类型',
    create_time     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time     datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user     int unsigned                               null comment '创建记录用户',
    update_user     int unsigned                               null comment '更新记录用户',
    mark            tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-表单审核通知表';

create table event_form_item
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    content       json                                       not null comment '表单内容',
    form_key      varchar(50)                                null comment '表单类型key',
    submit_number varchar(15)                                null comment '提交人学号',
    user_type     varchar(15)                                null comment '提交人用户类型',
    item_uuid     varchar(36)                                not null comment '表单唯一UUID',
    audit_step    tinyint unsigned default '1'               null comment '审核步骤1-4',
    audit_finish  tinyint unsigned default '0'               null comment '是否审核完结',
    audit_passed  tinyint unsigned default '0'               null comment '是否审核通过',
    create_user   int unsigned                               null comment '创建记录用户',
    update_user   int unsigned                               null comment '更新记录用户',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-用户提交的表单';

create index idx_form_uuid
    on event_form_item (item_uuid);

create index idx_submit_number
    on event_form_item (submit_number);

create table event_form_permission
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    form_id       bigint unsigned                            not null comment '通知ID',
    permission_id bigint unsigned                            not null comment '权限节点ID',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_form_id
    on event_form_permission (form_id);

#审核信息
create table event_form_reviewer
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    first_number  varchar(15)                                null comment '第一审核人',
    first_type    varchar(15)                                null comment '第一用户类型',
    second_number varchar(15)                                null comment '第二审核人',
    second_type   varchar(15)                                null comment '第二审核人类型',
    third_number  varchar(15)                                null comment '第三审核人',
    third_type    varchar(15)                                null comment '第三审核人类型',
    fourth_number varchar(15)                                null comment '第四审核人',
    fourth_type   varchar(15)                                null comment '第四审核人类型',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '表单审核流信息表';

create table event_reviewer_strategy
(
    id             bigint unsigned auto_increment comment '记录ID'
        primary key,
    form_key       varchar(50)                                not null comment '表单Key',
    strategy       varchar(15)                                not null comment '策略规则',
    strategy_value varchar(15)                                not null comment '策略值',
    reviewer_id    bigint unsigned                            not null comment '规则对应的审核流id',
    create_time    datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user    int unsigned                               null comment '创建用户',
    update_user    int unsigned                               null comment '更新用户',
    mark           tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '表单审核策略映射';

# 公告信息
create table event_todo_form
(
    id               bigint unsigned auto_increment comment '记录ID'
        primary key,
    title            varchar(50)                                not null comment '事项标题',
    initiator_number varchar(15)                                not null comment '发起人学号/工号',
    permissions      varchar(50)                                not null comment '接收者权限',
    form_key         varchar(50)                                not null comment '表单类型key',
    next_reviewer    varchar(15)                                null comment '下一步审核人，NULL为无需审核',
    create_time      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user      int unsigned                               null comment '创建记录用户',
    update_user      int unsigned                               null comment '更新记录用户',
    mark             tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-填报事项';

create table event_todo_form_permission
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    permission_id bigint unsigned                            not null comment '权限节点ID',
    form_id       bigint unsigned                            not null comment '通知ID',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_form_id
    on event_todo_form_permission (form_id);

create table event_todo_notice
(
    id               bigint unsigned auto_increment comment '记录ID'
        primary key,
    title            varchar(50)                                not null comment '代办事项标题',
    content          varchar(500)                               not null comment '通知内容',
    is_show          tinyint unsigned default '1'               not null comment '是否在客户端显示此通知',
    initiator_number varchar(15)                                null comment '发起者工号/学号',
    initiator_type   varchar(15)                                null comment '发起者用户类型',
    create_time      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user      int unsigned                               null comment '创建记录用户',
    update_user      int unsigned                               null comment '更新记录用户',
    mark             tinyint unsigned default '1'               null comment '记录有效性'
)
    comment '待办事项-通知信息表';

create table event_todo_notice_img
(
    id          bigint unsigned auto_increment comment '记录ID'
        primary key,
    uuid        varchar(40)                                not null comment '图片资源UUID',
    notice_id   bigint unsigned                            not null comment '通知ID',
    create_time datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user int unsigned                               null comment '创建用户',
    update_user int unsigned                               null comment '更新用户',
    mark        tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_notice_id
    on event_todo_notice_img (notice_id);

create table event_todo_notice_permission
(
    id            bigint unsigned auto_increment comment '记录ID'
        primary key,
    permission_id bigint unsigned                            not null comment '权限节点ID',
    notice_id     bigint unsigned                            not null comment '通知ID',
    create_time   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime         default CURRENT_TIMESTAMP null comment '更新时间',
    create_user   int unsigned                               null comment '创建用户',
    update_user   int unsigned                               null comment '更新用户',
    mark          tinyint unsigned default '1'               null comment '记录有效性'
);

create index idx_notice_id
    on event_todo_notice_permission (notice_id);

-- 组织信息
-- 学院
INSERT INTO entity_college (coll_name, coll_abbr, create_user, update_user, mark)
VALUES ('计算机工程学院', '计院', 1, 1, 1),
       ('机械工程学院', '机院', 1, 1, 1),
       ('电气工程学院', '电院', 1, 1, 1);
-- 部门
INSERT Into entity_department (dep_name, dep_abbr, create_user, update_user, mark)
values ('教务处', '教务处', 1, 1, 1),
       ('学生处', '学生处', 1, 1, 1),
       ('科研处', '科研处', 1, 1, 1);
-- 专业
INSERT INTO entity_major (maj_name, maj_abbr, college_id, create_user, update_user, mark)
VALUES ('软件工程', '软工', 1, 1, 1, 1),
       ('计算机科学与技术', '计科', 1, 1, 1, 1),
       ('人工智能', 'AI', 1, 1, 1, 1),
       ('机械设计制造及其自动化', '机械设计', 2, 1, 1, 1),
       ('自动化', '自动化', 3, 1, 1, 1),
       ('电气工程及其自动化', '电气', 3, 1, 1, 1);

-- 班级
INSERT INTO entity_classes (class_name, teacher_number, counselor_number, assistant_number, college_id, major_id,
                            create_user, update_user, mark)
VALUES ('22软工6班专升本', 'T10001', 'C10001', 'A10001', 1, 1, 1, 1, 1),
       ('22计科1班', 'T10002', 'C10002', 'A10002', 1, 2, 1, 1, 1),
       ('22AI2班', 'T10003', 'C10003', 'A10003', 1, 3, 1, 1, 1),
       ('22机械设计1班', 'T20001', 'C20001', 'A20001', 2, 4, 1, 1, 1),
       ('22自动化1班', 'T30001', 'C30001', 'A30001', 3, 5, 1, 1, 1),
       ('22电气1班', 'T30002', 'C30002', 'A30002', 3, 6, 1, 1, 1),
       ('21软工1班', 'TEACH007', 'COUNS003', 'A20001', 1, 1, 1, 1, 1);

-- 学生信息
INSERT INTO user_student (real_name, stu_number, phone, id_card, gender, nation, email, qq, politic, serial_number,
                          exam_number, stu_status, college_id, major_id, classes_id, create_user, update_user, mark)
VALUES ('张三', '20220101', '13100000001', '360101199001011234', '男', '汉', 'zhangsan@example.com', '10001', '群众',
        'TJ20220101', 'E20220101', '在读', 1, 1, 1, 1, 1, 1),
       ('李四', '20220102', '13100000002', '360101199002021234', '男', '汉', 'lisi@example.com', '10002', '群众',
        'TJ20220102', 'E20220102', '在读', 1, 1, 1, 1, 1, 1),
       ('王五', '20220103', '13100000003', '360101199003031234', '男', '汉', 'wangwu@example.com', '10003', '群众',
        'TJ20220103', 'E20220103', '在读', 1, 1, 1, 1, 1, 1),
       ('赵六', '20220104', '13100000004', '360101199004041234', '男', '汉', 'zhaoliu@example.com', '10004', '群众',
        'TJ20220104', 'E20220104', '在读', 1, 1, 1, 1, 1, 1),
       ('周七', '20220105', '13100000005', '360101199005051234', '男', '汉', 'zhouqi@example.com', '10005', '群众',
        'TJ20220105', 'E20220105', '在读', 1, 1, 1, 1, 1, 1),

       ('丁一', '20220401', '13200000001', '360101199016161234', '女', '汉', 'dingyi@example.com', '40001', '群众',
        'TJ20220401', 'E20220401', '在读', 1, 2, 2, 1, 1, 1),
       ('何二', '20220402', '13200000002', '360101199017171234', '女', '汉', 'heer@example.com', '40002', '群众',
        'TJ20220402', 'E20220402', '在读', 1, 2, 2, 1, 1, 1),
       ('刘三', '20220403', '13200000003', '360101199018181234', '女', '汉', 'liusang@example.com', '40003', '群众',
        'TJ20220403', 'E20220403', '在读', 1, 2, 2, 1, 1, 1),
       ('江四', '20220404', '13200000004', '360101199019191234', '女', '汉', 'jiangsi@example.com', '40004', '群众',
        'TJ20220404', 'E20220404', '在读', 1, 2, 2, 1, 1, 1),
       ('宋五', '20220405', '13200000005', '360101199020201234', '女', '汉', 'songwu@example.com', '40005', '群众',
        'TJ20220405', 'E20220405', '在读', 1, 2, 2, 1, 1, 1),

       ('杨一', '20220501', '13300000001', '360101199021211234', '女', '汉', 'yangyi@example.com', '50001', '群众',
        'TJ20220501', 'E20220501', '在读', 1, 3, 3, 1, 1, 1),
       ('吕二', '20220502', '13300000002', '360101199022221234', '女', '汉', 'lver@example.com', '50002', '群众',
        'TJ20220502', 'E20220502', '在读', 1, 3, 3, 1, 1, 1),
       ('张三', '20220503', '13300000003', '360101199023231234', '女', '汉', 'zhangsan@example.com', '50003', '群众',
        'TJ20220503', 'E20220503', '在读', 1, 3, 3, 1, 1, 1),
       ('李四', '20220504', '13300000004', '360101199024241234', '女', '汉', 'lisi@example.com', '50004', '群众',
        'TJ20220504', 'E20220504', '在读', 1, 3, 3, 1, 1, 1),
       ('赵五', '20220505', '13300000005', '360101199025251234', '女', '汉', 'zhaowu@example.com', '50005', '群众',
        'TJ20220505', 'E20220505', '在读', 1, 3, 3, 1, 1, 1),

       ('孙八', '20220201', '13100000006', '360101199006061234', '男', '汉', 'sunba@example.com', '20001', '群众',
        'TJ20220201', 'E20220201', '在读', 2, 4, 4, 1, 1, 1),
       ('吴九', '20220202', '13100000007', '360101199007071234', '男', '汉', 'wujiu@example.com', '20002', '群众',
        'TJ20220202', 'E20220202', '在读', 2, 4, 4, 1, 1, 1),
       ('郑十', '20220203', '13100000008', '360101199008081234', '男', '汉', 'zhengshi@example.com', '20003', '群众',
        'TJ20220203', 'E20220203', '在读', 2, 4, 4, 1, 1, 1),
       ('王十一', '20220204', '13100000009', '360101199009091234', '男', '汉', 'wangshiyi@example.com', '20004', '群众',
        'TJ20220204', 'E20220204', '在读', 2, 4, 4, 1, 1, 1),
       ('李十二', '20220205', '13100000010', '360101199010101234', '男', '汉', 'lishier@example.com', '20005', '群众',
        'TJ20220205', 'E20220205', '在读', 2, 4, 4, 1, 1, 1),

       ('陈一', '20220301', '13100000011', '360101199011111234', '男', '汉', 'chenyi@example.com', '30001', '群众',
        'TJ20220301', 'E20220301', '在读', 3, 6, 6, 1, 1, 1),
       ('周二', '20220302', '13100000012', '360101199012121234', '男', '汉', 'zhouer@example.com', '30002', '群众',
        'TJ20220302', 'E20220302', '在读', 3, 6, 6, 1, 1, 1),
       ('吴三', '20220303', '13100000013', '360101199013131234', '男', '汉', 'wusan@example.com', '30003', '群众',
        'TJ20220303', 'E20220303', '在读', 3, 6, 6, 1, 1, 1),
       ('郑四', '20220304', '13100000014', '360101199014141234', '男', '汉', 'zhengsi@example.com', '30004', '群众',
        'TJ20220304', 'E20220304', '在读', 3, 6, 6, 1, 1, 1),
       ('王五', '20220305', '13100000015', '360101199015151234', '男', '汉', 'wangwu@example.com', '30005', '群众',
        'TJ20220305', 'E20220305', '在读', 3, 6, 6, 1, 1, 1),

       # 21级学生
       ('学生A', '2101001', '13300000018', '360101199912261234', '男', '汉', 'studenta@example.com', '90010', '群众',
        'TJ2101001', 'E2101001', '在读', 1, 1, 7, 1, 1, 1),
       ('学生B', '2101002', '13300000019', '360101199911251234', '女', '汉', 'studentb@example.com', '90011', '群众',
        'TJ2101002', 'E2101002', '在读', 1, 1, 7, 1, 1, 1),
       ('学生C', '2101003', '13300000020', '360101199910241234', '男', '汉', 'studentc@example.com', '90012', '群众',
        'TJ2101003', 'E2101003', '在读', 1, 1, 7, 1, 1, 1);

-- 教师信息
-- 插入辅导员和班主任信息
INSERT INTO user_teacher (real_name, tch_number, gender, phone, email, qq, office, password, identity, college_id,
                          create_user, update_user, mark)
VALUES ('辅导员A', 'COUNS001', '女', '13200000010', 'counsela@example.com', '90001', '办公室1', 'password', '辅导员', 1,
        1, 1, 1),
       ('辅导员B', 'COUNS002', '男', '13200000011', 'counselb@example.com', '90002', '办公室2', 'password', '辅导员', 1,
        1, 1, 1),
       ('班主任1', 'TEACH001', '女', '13200000012', 'teacher1@example.com', '80001', '办公室3', 'password', '班主任', 1,
        1, 1, 1),
       ('班主任2', 'TEACH002', '男', '13200000013', 'teacher2@example.com', '80002', '办公室4', 'password', '班主任', 1,
        1, 1, 1),
       ('班主任3', 'TEACH003', '女', '13200000014', 'teacher3@example.com', '80003', '办公室5', 'password', '班主任', 2,
        1, 1, 1),
       ('班主任4', 'TEACH004', '男', '13200000015', 'teacher4@example.com', '80004', '办公室6', 'password', '班主任', 2,
        1, 1, 1),
       ('班主任5', 'TEACH005', '女', '13200000016', 'teacher5@example.com', '80005', '办公室7', 'password', '班主任', 3,
        1, 1, 1),
       ('班主任6', 'TEACH006', '男', '13200000017', 'teacher6@example.com', '80006', '办公室8', 'password', '班主任', 3,
        1, 1, 1);
-- 为班级分配辅导员和班主任
UPDATE entity_classes
SET counselor_number = 'COUNS001'
WHERE id IN (1, 2, 3);
UPDATE entity_classes
SET counselor_number = 'COUNS002'
WHERE id IN (4, 5, 6);
UPDATE entity_classes
SET teacher_number = 'TEACH001'
WHERE id = 1;
UPDATE entity_classes
SET teacher_number = 'TEACH002'
WHERE id = 2;
UPDATE entity_classes
SET teacher_number = 'TEACH003'
WHERE id = 3;
UPDATE entity_classes
SET teacher_number = 'TEACH004'
WHERE id = 4;
UPDATE entity_classes
SET teacher_number = 'TEACH005'
WHERE id = 5;
UPDATE entity_classes
SET teacher_number = 'TEACH006'
WHERE id = 6;

-- 字段表
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '学院', 'college', 1);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '部门', 'department', 2);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '姓名', 'name', 3);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '班级', 'classes', 4);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '学号', 'number', 5);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '身份证后六位', 'idCard_6', 6);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '身份证', 'idCard', 7);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '性别', 'gender', 8);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '民族', 'nation', 9);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '邮箱', 'email', 10);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '手机号', 'phone', 11);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:07', '2021-08-24 19:34:07', '政治面貌', 'police', 12);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '微信号', 'wechat', 13);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', 'QQ', 'qq', 14);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '班级人数', 'classNum', 15);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '已绑定人数', 'boundNum', 16);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '办公室', 'office', 17);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '身份', 'identity', 18);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '教师', 'teacher', 19);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '辅导员', 'counselor', 20);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '助理辅导员(助辅)', 'assistant', 21);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '地址', 'place', 22);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '备注', 'remark', 23);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-24 19:34:08', '2021-08-24 19:34:08', '报到时间', 'reportTime', 24);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:50', '2021-08-30 16:32:50', '照片', 'image', 27);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:50', '2021-08-30 16:32:50', '民族', 'nation', 28);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '政治面貌', 'politicalIdentity', 29);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '生源地', 'originPlace', 30);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '户籍所在地', 'housePlace', 31);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '学生本人手机号', 'stuPhone', 32);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '录取通知书编号', 'matriculateId', 33);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '班级名称', 'className', 34);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '身高(CM)', 'height', 35);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '体重(KG)', 'weight', 36);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', 'T恤尺寸', 'clothingSize', 37);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '入校前就读学校', 'formerSchool', 38);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '是否需要走绿色通道', 'greenChannel', 39);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '姓名', 'name', 40);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '家庭住址', 'homeAddress', 41);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '具体住址', 'specificAddress', 42);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '家庭住址邮编', 'postalCode', 43);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '是否为独生子女', 'isOnlyChild', 44);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:51', '2021-08-30 16:32:51', '家庭人口总数', 'homePopulation', 45);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '家庭经济情况', 'familyEco', 46);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '学生姓名', 'stuName', 47);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一与学生本人关系', 'relationship', 48);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一姓名', 'kinsfolkName', 49);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一年龄', 'kinsfolkAge', 50);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一教育背景', 'kinsfolkEduBgd', 51);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一本人手机号', 'kinsfolkPhone', 52);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一工作单位', 'workUnit', 53);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一工作职务', 'workDuty', 54);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一年收入', 'kinsfolkEco', 55);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属一健康状况', 'kinsfolkHealth', 56);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二与学生本人关系', 'relationship2', 57);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二姓名', 'kinsfolkName2', 58);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二年龄', 'kinsfolkAge2', 59);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二教育背景', 'kinsfolkEduBgd2', 60);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二本人手机号', 'kinsfolkPhone2', 61);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二工作单位', 'workUnit2', 62);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:52', '2021-08-30 16:32:52', '亲属二工作职务', 'workDuty2', 63);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属二年收入', 'kinsfolkEco2', 64);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属二健康状况', 'kinsfolkHealth2', 65);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三与学生本人关系', 'relationship3', 66);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三姓名', 'kinsfolkName3', 67);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三年龄', 'kinsfolkAge3', 68);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三教育背景', 'kinsfolkEduBgd3', 69);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三本人手机号', 'kinsfolkPhone3', 70);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三工作单位', 'workUnit3', 71);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三工作职务', 'workDuty3', 72);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三年收入', 'kinsfolkEco3', 73);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '亲属三健康状况', 'kinsfolkHealth3', 74);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '个人兴趣爱好', 'interest', 75);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '高中获奖经历', 'awardHistory', 76);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '高中担任班干部情况', 'dutyHistory', 77);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '希望担任何种班级干部', 'hopeDuty', 78);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '大学的期望和四年后的目标', 'expectation', 79);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '想对辅导员说的一句话', 'saidText', 80);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '对自己性格的描述', 'characterDescription', 81);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:53', '2021-08-30 16:32:53', '对自己家庭的描述', 'familyDescription', 82);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '本人能否按时报到', 'comeBackOnTime', 83);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '原因', 'reason', 84);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '目前所在地', 'currentLocation', 85);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '出发地', 'departureLocation', 86);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '具体出发地址', 'departureAddress', 87);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '交通工具', 'vehicle', 88);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '交通工具编号', 'vehicleNumber', 89);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '交通工具座位号', 'seatNumber', 90);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '到达地点', 'arrivalAddress', 91);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '预计出发时间', 'departureTime', 92);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:54', '2021-08-30 16:32:54', '预计到达时间', 'arrivalTime', 93);
INSERT INTO wechat_mapper (mark, update_user, create_user, update_time, create_time, mapper_value, mapper_key, id)
VALUES (1, null, null, '2021-08-30 16:32:50', '2021-08-30 16:32:50', '审核状态', 'passed', 94);

-- 小程序路由
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (1, 6, '活动', 'icon-huodong', 'page', 'activityList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null, null,
        1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (2, 6, '我的公告', 'icon-gonggao', 'page', 'noticeList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (3, 6, '我的班级', 'icon-xuesheng', 'page', 'classesInfo', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (4, 6, '信息填报', 'icon-tianbao', 'page', 'formList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null, null,
        1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (5, 7, '班级管理', 'icon-banji', 'page', 'classesList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null, null,
        1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (6, 7, '我的学生', 'icon-xuesheng', 'page', 'studentList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (7, 7, '我的审核', 'icon-shenpi', 'page', 'examineList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (8, 0, '功能房', 'icon-jiaoshi', 'page', 'room', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null, null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (9, 7, '活动', 'icon-huodong', 'page', 'activityList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null, null,
        1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (10, 7, '我的公告', 'icon-gonggao', 'page', 'noticeList', '2021-08-28 13:13:28', '2021-08-28 13:13:28', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (11, 8, '我的审核', 'icon-shenpi', 'page', 'examineList', '2021-08-30 00:02:53', '2021-08-30 00:02:53', null,
        null, 1);
INSERT INTO registration_system.wechat_route (id, role_id, route_name, icon, type, url_name, create_time, update_time,
                                              create_user, update_user, mark)
VALUES (13, 8, '我的学生', 'icon-xuesheng', 'page', 'studentList', '2021-08-30 22:34:44', '2021-08-30 22:34:44', null,
        null, 1);

-- 权限节点数据插入
INSERT INTO permission_node (level1, level2, level3, level4, create_user, update_user, mark)
VALUES ('计算机工程学院', '软件工程', '22软工6班专升本', '*', 1, 1, 1),
       ('计算机工程学院', '计算机科学与技术', '22计科1班', '*', 1, 1, 1),
       ('计算机工程学院', '人工智能', '22AI2班', '*', 1, 1, 1),
       ('机械工程学院', '机械设计制造及其自动化', '22机械设计1班', '*', 1, 1, 1),
       ('电气工程学院', '自动化', '22自动化1班', '*', 1, 1, 1),
       ('电气工程学院', '电气工程及其自动化', '22电气1班', '*', 1, 1, 1);

-- 教师权限分配
-- 假设每个班主任管理自己的班级，每个辅导员管理三个班级
INSERT INTO user_teacher_permission (tch_number, permission_id, create_user, update_user, mark)
VALUES ('TEACH001', 1, 1, 1, 1),
       ('TEACH002', 2, 1, 1, 1),
       ('TEACH003', 3, 1, 1, 1),
       ('TEACH004', 4, 1, 1, 1),
       ('TEACH005', 5, 1, 1, 1),
       ('TEACH006', 6, 1, 1, 1),
-- 辅导员A管理的班级
       ('COUNS001', 1, 1, 1, 1),
       ('COUNS001', 2, 1, 1, 1),
       ('COUNS001', 3, 1, 1, 1),
-- 辅导员B管理的班级
       ('COUNS002', 4, 1, 1, 1),
       ('COUNS002', 5, 1, 1, 1),
       ('COUNS002', 6, 1, 1, 1);
-- 假设权限节点ID 8, 9, 10分别对应于'22软工6班专升本', '22计科1班', '22AI2班'
-- 学生A管理'22软工6班专升本', '22计科1班'
INSERT INTO user_student_permission (stu_number, permission_id, create_user, update_user, mark)
VALUES ('2101001', 1, 1, 1, 1),
       ('2101001', 2, 1, 1, 1);
-- 学生B管理'22AI2班', '22机械设计1班'
INSERT INTO user_student_permission (stu_number, permission_id, create_user, update_user, mark)
VALUES ('2101002', 3, 1, 1, 1),
       ('2101002', 4, 1, 1, 1);
-- 学生C管理'22自动化1班', '22电气1班'
INSERT INTO user_student_permission (stu_number, permission_id, create_user, update_user, mark)
VALUES ('2101003', 5, 1, 1, 1),
       ('2101003', 6, 1, 1, 1);


-- 学生A作为助理辅导员管理'22软工6班专升本'和'22计科1班'
UPDATE entity_classes
SET assistant_number = '2101001'
WHERE class_name IN ('22软工6班专升本', '22计科1班');

-- 学生B作为助理辅导员管理'22AI2班'和'22机械设计1班'
UPDATE entity_classes
SET assistant_number = '2101002'
WHERE class_name IN ('22AI2班', '22机械设计1班');

-- 学生C作为助理辅导员管理'22自动化1班'和'22电气1班'
UPDATE entity_classes
SET assistant_number = '2101003'
WHERE class_name IN ('22自动化1班', '22电气1班');

-- 添加用户角色
INSERT Into user_role (user_number, user_type, role_id, update_user)
    value (20220101, 'student', 6, 1),
    (2101001,'student',6,1),
    (2101001,'student',8,1);

-- 表单信息
INSERT INTO registration_system.event_form (id, title, initiator_number, initiator_type, form_key, template, audit, reviewer_id, start_time, end_time, enable_strategy, strategy, create_time, update_time, create_user, update_user, mark)
VALUES
    (1, '基本信息', 'COUNS001', 'teacher', '3z9xhb1qHZ2', '{"formId": "1", "formInfo": [{"id": "image", "data": [], "rule": {"msg": "请上传本人证件照", "type": "count", "amount": 1}, "type": "upload", "label": "证件照", "output": "uuid", "capture": ["album", "camera"], "maxSize": 3145728, "maxCount": 2, "sizeType": ["compressed"], "acceptType": "image", "placeholder": "点击上传本人证件照"}, {"id": "nation", "data": [{"id": 0, "text": "汉族"}, {"id": 1, "text": "满族"}, {"id": 2, "text": "蒙古族"}, {"id": 3, "text": "回族"}, {"id": 4, "text": "藏族"}, {"id": 5, "text": "维吾尔族"}, {"id": 6, "text": "苗族"}, {"id": 7, "text": "彝族"}, {"id": 8, "text": "壮族"}, {"id": 9, "text": "布依族"}, {"id": 10, "text": "侗族"}, {"id": 11, "text": "瑶族"}, {"id": 12, "text": "白族"}, {"id": 13, "text": "土家族"}, {"id": 14, "text": "哈尼族"}, {"id": 15, "text": "哈萨克族"}, {"id": 16, "text": "傣族"}, {"id": 17, "text": "黎族"}, {"id": 18, "text": "傈僳族"}, {"id": 19, "text": "佤族"}, {"id": 20, "text": "畲族"}, {"id": 21, "text": "高山族"}, {"id": 22, "text": "拉祜族"}, {"id": 23, "text": "水族"}, {"id": 24, "text": "东乡族"}, {"id": 25, "text": "纳西族"}, {"id": 26, "text": "景颇族"}, {"id": 27, "text": "柯尔克孜族"}, {"id": 28, "text": "土族"}, {"id": 29, "text": "达斡尔族"}, {"id": 30, "text": "仫佬族"}, {"id": 31, "text": "羌族"}, {"id": 32, "text": "布朗族"}, {"id": 33, "text": "撒拉族"}, {"id": 34, "text": "毛南族"}, {"id": 35, "text": "仡佬族"}, {"id": 36, "text": "锡伯族"}, {"id": 37, "text": "阿昌族"}, {"id": 38, "text": "普米族"}, {"id": 39, "text": "朝鲜族"}, {"id": 40, "text": "塔吉克族"}, {"id": 41, "text": "怒族"}, {"id": 42, "text": "乌孜别克族"}, {"id": 43, "text": "俄罗斯族"}, {"id": 44, "text": "鄂温克族"}, {"id": 45, "text": "德昂族"}, {"id": 46, "text": "保安族"}, {"id": 47, "text": "裕固族"}, {"id": 48, "text": "京族"}, {"id": 49, "text": "塔塔尔族"}, {"id": 50, "text": "独龙族"}, {"id": 51, "text": "鄂伦春族"}, {"id": 52, "text": "赫哲族"}, {"id": 53, "text": "门巴族"}, {"id": 54, "text": "珞巴族"}, {"id": 55, "text": "基诺族"}], "rule": {"msg": "点击选择你的民族", "type": "notnull"}, "type": "picker", "label": "民族", "output": "text", "placeholder": "点击选择你的民族", "defaultValue": ""}, {"id": "politicalIdentity", "data": [{"id": 0, "text": "中共党员"}, {"id": 1, "text": "中共预备党员"}, {"id": 2, "text": "共青团员"}, {"id": 3, "text": "民革党员"}, {"id": 4, "text": "民盟盟员"}, {"id": 5, "text": "民建会员"}, {"id": 6, "text": "民进会员"}, {"id": 7, "text": "农工党党员"}, {"id": 8, "text": "致公党党员"}, {"id": 9, "text": "九三学社社员"}, {"id": 10, "text": "台盟盟员"}, {"id": 11, "text": "无党派人士"}, {"id": 12, "text": "群众"}], "rule": {"msg": "请选择你的政治面貌", "type": "notnull"}, "type": "picker", "label": "政治面貌", "output": "text", "placeholder": "点击选择你的政治面貌", "defaultValue": ""}, {"id": "originPlace", "data": [], "rule": {"msg": "请选择生源地", "type": "notnull"}, "type": "region", "label": "生源地", "placeholder": "请选择生源地"}, {"id": "housePlace", "data": [], "rule": {"msg": "请选择户籍所在地", "type": "notnull"}, "type": "region", "label": "户籍所在地", "placeholder": "请选择户籍所在地"}, {"id": "stuPhone", "data": [], "rule": {"msg": "输入的手机号不合法", "reg": "^1[0-9]{10}$", "type": "reg"}, "type": "input", "label": "学生本人手机号", "inputType": "number", "maxlength": 11, "placeholder": "请输入你个人的手机号"}, {"id": "email", "data": [], "rule": {"msg": "请输入你个人的电子邮箱", "type": "notnull"}, "type": "input", "label": "学生本人邮箱", "inputType": "text", "placeholder": "请输入你个人的电子邮箱"}, {"id": "qq", "data": [], "rule": {"msg": "请输入你个人的qq号", "type": "null"}, "type": "input", "label": "学生本人QQ号", "inputType": "text", "placeholder": "请输入你个人的qq号"}, {"id": "wechat", "data": [], "rule": {"msg": "请输入你个人的微信号", "type": "null"}, "type": "input", "label": "学生本人微信号", "inputType": "text", "placeholder": "请输入你个人的微信号"}, {"id": "matriculateId", "data": [], "rule": {"msg": "请输入录取通知书编号", "type": "notnull"}, "type": "input", "label": "录取通知书编号", "inputType": "text", "placeholder": "请输入录取通知书编号"}, {"id": "className", "data": [], "rule": {"msg": "请输入你的班级名称", "type": "notnull"}, "type": "input", "label": "班级名称", "inputType": "text", "placeholder": "请输入你的班级名称"}, {"id": "height", "data": [], "rule": {"msg": "请输入你的身高", "type": "notnull"}, "type": "input", "label": "身高(CM)", "inputType": "Number", "maxlength": 3, "placeholder": "请输入你的身高"}, {"id": "weight", "data": [], "rule": {"msg": "请输入你的体重", "type": "notnull"}, "type": "input", "label": "体重(KG)", "inputType": "Number", "maxlength": 3, "placeholder": "请输入你的体重"}, {"id": "clothingSize", "data": [], "rule": {"msg": "请输入你的T恤尺寸", "type": "null"}, "type": "input", "label": "T恤尺寸", "inputType": "text", "placeholder": "请输入你的T恤尺寸"}, {"id": "formerSchool", "data": [], "rule": {"msg": "请输入你的上一所学校", "type": "notnull"}, "type": "input", "label": "入校前就读学校", "inputType": "text", "placeholder": "请输入你的上一所学校"}, {"id": "greenChannel", "tip": " 为切实保证家庭经济困难学生顺利入学，我校对被录取入学的家庭经济困难新生，先办理入学手续，后通过助学贷款（校园地国家助学贷款或生源地信用助学贷款）等方式解决其学费和住宿费。入学后根据实际情况开展困难认定，采取不同措施给予资助。", "data": [{"id": 0, "text": "是"}, {"id": 1, "text": "否"}], "rule": {"msg": "请选择是否需要走绿色通道", "reg": "", "type": "notnull"}, "type": "radio", "label": "是否需要走绿色通道", "defaultValue": ""}], "formName": "个人基本信息填报", "formIntro": "", "createDate": "2021/8/30 12:00:00"}', 1, 1, '2021-08-30 12:00:00', '2021-09-10 17:07:23', 1, 'class', '2021-08-18 17:07:27', '2021-08-18 17:07:27', null, null, 1),
    (2, '家庭信息', 'COUNS001', 'teacher', '8beREQIxN2r', '{"formId": "2", "formInfo": [{"id": "name", "tip": "", "data": [], "rule": {"msg": "名字不能为空", "type": "notnull"}, "type": "input", "label": "姓名", "inputType": "text", "maxlength": -1, "placeholder": "请输入你的姓名"}, {"id": "homeAddress", "data": [], "rule": {"msg": "家庭住址不能为空", "type": "notnull"}, "type": "region", "label": "家庭住址", "placeholder": "点击选择你的家庭住址"}, {"id": "specificAddress", "tip": "", "data": [], "rule": {"msg": "请输入你的具体住址", "type": "notnull"}, "type": "input", "label": "具体住址", "inputType": "text", "maxlength": -1, "placeholder": "请输入你的具体住址"}, {"id": "postalCode", "tip": "", "data": [], "rule": {"msg": "请输入你的家庭住址邮编", "type": "notnull"}, "type": "input", "label": "家庭住址邮编", "inputType": "number", "maxlength": -1, "placeholder": "请输入你的家庭住址邮编"}, {"id": "isOnlyChild", "data": [{"id": 0, "text": "是"}, {"id": 1, "text": "否"}], "rule": {"msg": "请选择您是否为独生子女", "type": "notnull"}, "type": "radio", "label": "是否为独生子女", "defaultValue": ""}, {"id": "homePopulation", "tip": "", "data": [], "rule": {"msg": "请输入你的家庭人口总数", "type": "notnull"}, "type": "input", "label": "家庭人口总数", "inputType": "number", "maxlength": -1, "placeholder": "请输入你的家庭人口总数"}, {"id": "familyEco", "tip": "请根据家庭年收入情况选择合适的选项", "data": [{"id": 0, "text": "一万及以下"}, {"id": 1, "text": "一万至五万"}, {"id": 2, "text": "五万至十五万"}, {"id": 3, "text": "十五万至三十万"}, {"id": 4, "text": "三十万及以上"}], "rule": {"msg": "请选择你的家庭经济情况", "type": "notnull"}, "type": "picker", "label": "家庭经济情况", "placeholder": "点击选择你的家庭经济情况"}], "formName": "个人家庭信息填报", "formIntro": "", "createDate": "2021/8/30 12:00:00"}', 1, 1, '2021-08-30 12:00:00', '2021-09-10 17:07:23', 1, 'class', '2021-08-18 17:07:27', '2021-08-18 17:07:59', null, null, 1),
    (3, '家庭成员信息', 'COUNS001', 'teacher', 'zo2I8y5H1lq', '{"formId": "3", "formInfo": [{"id": "stuName", "tip": "", "data": [], "rule": {"msg": "请输入学生姓名", "type": "notnull"}, "type": "input", "label": "学生姓名", "inputType": "text", "maxlength": -1, "placeholder": "请输入学生姓名"}, {"id": "relationship", "tip": "建议选择填写直系亲属", "data": [{"id": 0, "text": "父亲"}, {"id": 1, "text": "母亲"}, {"id": 2, "text": "爷爷"}, {"id": 3, "text": "奶奶"}, {"id": 4, "text": "外公"}, {"id": 5, "text": "外婆"}, {"id": 6, "text": "姐姐"}, {"id": 7, "text": "妹妹"}, {"id": 8, "text": "哥哥"}, {"id": 9, "text": "弟弟"}, {"id": 10, "text": "叔伯"}, {"id": 11, "text": "姑母"}, {"id": 12, "text": "舅舅"}], "rule": {"msg": "请选择亲属一与学生本人关系", "type": "notnull"}, "type": "picker", "label": "亲属一与学生本人关系", "output": "text", "placeholder": "请选择亲属一与学生本人关系", "defaultValue": ""}, {"id": "kinsfolkName", "tip": "", "data": [], "rule": {"msg": "请输入亲属一姓名", "type": "notnull"}, "type": "input", "label": "亲属一姓名", "inputType": "text", "maxlength": -1, "placeholder": "请输入亲属一姓名"}, {"id": "kinsfolkAge", "tip": "", "data": [], "rule": {"msg": "请输入亲属一年龄", "type": "notnull"}, "type": "input", "label": "亲属一年龄", "inputType": "number", "maxlength": 3, "placeholder": "请输入亲属一年龄"}, {"id": "kinsfolkEduBgd", "data": [{"id": 0, "text": "小学"}, {"id": 1, "text": "初中"}, {"id": 2, "text": "高中"}, {"id": 3, "text": "专科"}, {"id": 4, "text": "本科"}, {"id": 5, "text": "硕士研究生"}, {"id": 6, "text": "博士研究生"}], "rule": {"msg": "请选择亲属一教育背景", "type": "notnull"}, "type": "picker", "label": "亲属一教育背景", "output": "text", "placeholder": "请选择亲属一教育背景", "defaultValue": ""}, {"id": "kinsfolkPhone", "data": [], "rule": {"msg": "亲属一本人手机号不合法", "reg": "^1[0-9]{10}$", "type": "reg"}, "type": "input", "label": "亲属一本人手机号", "inputType": "number", "maxlength": 11, "placeholder": "请输入亲属一本人手机号"}, {"id": "workUnit", "data": [], "rule": {"msg": "请输入亲属一工作单位", "type": "notnull"}, "type": "input", "label": "亲属一工作单位", "inputType": "text", "placeholder": "请输入亲属一工作单位"}, {"id": "workDuty", "data": [], "rule": {"msg": "请输入亲属一工作职务", "type": "notnull"}, "type": "input", "label": "亲属一工作职务", "inputType": "text", "placeholder": "请输入亲属一工作职务"}, {"id": "kinsfolkEco", "data": [{"id": 0, "text": "一万及以下"}, {"id": 1, "text": "一万至五万"}, {"id": 2, "text": "五万至十五万"}, {"id": 3, "text": "十五万至三十万"}, {"id": 4, "text": "三十万及以上"}], "rule": {"msg": "请选择亲属一年收入", "type": "notnull"}, "type": "picker", "label": "亲属一年收入", "placeholder": "点击选择亲属一年收入"}, {"id": "kinsfolkHealth", "data": [], "rule": {"msg": "请输入亲属一健康状况", "type": "notnull"}, "type": "input", "label": "亲属一健康状况", "inputType": "text", "placeholder": "请输入亲属一健康状况"}, {"id": "relationship2", "tip": "建议选择填写直系亲属", "data": [{"id": 0, "text": "父亲"}, {"id": 1, "text": "母亲"}, {"id": 2, "text": "爷爷"}, {"id": 3, "text": "奶奶"}, {"id": 4, "text": "外公"}, {"id": 5, "text": "外婆"}, {"id": 6, "text": "姐姐"}, {"id": 7, "text": "妹妹"}, {"id": 8, "text": "哥哥"}, {"id": 9, "text": "弟弟"}, {"id": 10, "text": "叔伯"}, {"id": 11, "text": "姑母"}, {"id": 12, "text": "舅舅"}], "rule": {"msg": "请选择亲属二与学生本人关系", "type": "null"}, "type": "picker", "label": "亲属二与学生本人关系", "output": "text", "placeholder": "请选择亲属二与学生本人关系", "defaultValue": ""}, {"id": "kinsfolkName2", "tip": "", "data": [], "rule": {"msg": "请输入亲属二姓名", "type": "null"}, "type": "input", "label": "亲属二姓名", "inputType": "text", "maxlength": -1, "placeholder": "请输入亲属二姓名"}, {"id": "kinsfolkAge2", "tip": "", "data": [], "rule": {"msg": "请输入亲属二年龄", "type": "null"}, "type": "input", "label": "亲属二年龄", "inputType": "number", "maxlength": 3, "placeholder": "请输入亲属二年龄"}, {"id": "kinsfolkEduBgd2", "data": [{"id": 0, "text": "小学"}, {"id": 1, "text": "初中"}, {"id": 2, "text": "高中"}, {"id": 3, "text": "专科"}, {"id": 4, "text": "本科"}, {"id": 5, "text": "硕士研究生"}, {"id": 6, "text": "博士研究生"}], "rule": {"msg": "请选择亲属二教育背景", "type": "null"}, "type": "picker", "label": "亲属二教育背景", "output": "text", "placeholder": "请选择亲属二教育背景", "defaultValue": ""}, {"id": "kinsfolkPhone2", "data": [], "rule": {"msg": "请输入亲属二本人手机号", "type": "null"}, "type": "input", "label": "亲属二本人手机号", "inputType": "Number", "maxlength": 11, "placeholder": "请输入亲属二本人手机号"}, {"id": "workUnit2", "data": [], "rule": {"msg": "请输入亲属二工作单位", "type": "null"}, "type": "input", "label": "亲属二工作单位", "inputType": "text", "placeholder": "请输入亲属二工作单位"}, {"id": "workDuty2", "data": [], "rule": {"msg": "请输入亲属二工作职务", "type": "null"}, "type": "input", "label": "亲属二工作职务", "inputType": "text", "placeholder": "请输入亲属二工作职务"}, {"id": "kinsfolkEco2", "data": [{"id": 0, "text": "一万及以下"}, {"id": 1, "text": "一万至五万"}, {"id": 2, "text": "五万至十五万"}, {"id": 3, "text": "十五万至三十万"}, {"id": 4, "text": "三十万及以上"}], "rule": {"msg": "请选择亲属二年收入", "type": "null"}, "type": "picker", "label": "亲属二年收入", "placeholder": "点击选择亲属二年收入"}, {"id": "kinsfolkHealth2", "data": [], "rule": {"msg": "请输入亲属二健康状况", "type": "null"}, "type": "input", "label": "亲属二健康状况", "inputType": "text", "placeholder": "请输入亲属二健康状况"}, {"id": "relationship3", "tip": "建议选择填写直系亲属", "data": [{"id": 0, "text": "父亲"}, {"id": 1, "text": "母亲"}, {"id": 2, "text": "爷爷"}, {"id": 3, "text": "奶奶"}, {"id": 4, "text": "外公"}, {"id": 5, "text": "外婆"}, {"id": 6, "text": "姐姐"}, {"id": 7, "text": "妹妹"}, {"id": 8, "text": "哥哥"}, {"id": 9, "text": "弟弟"}, {"id": 10, "text": "叔伯"}, {"id": 11, "text": "姑母"}, {"id": 12, "text": "舅舅"}], "rule": {"msg": "请选择亲属三与学生本人关系", "type": "null"}, "type": "picker", "label": "亲属三与学生本人关系", "output": "text", "placeholder": "请选择亲属三与学生本人关系", "defaultValue": ""}, {"id": "kinsfolkName3", "tip": "", "data": [], "rule": {"msg": "请输入亲属三姓名", "type": "null"}, "type": "input", "label": "亲属三姓名", "inputType": "text", "maxlength": -1, "placeholder": "请输入亲属三姓名"}, {"id": "kinsfolkAge3", "tip": "", "data": [], "rule": {"msg": "请输入亲属三年龄", "type": "null"}, "type": "input", "label": "亲属三年龄", "inputType": "number", "maxlength": 3, "placeholder": "请输入亲属三年龄"}, {"id": "kinsfolkEduBgd3", "data": [{"id": 0, "text": "小学"}, {"id": 1, "text": "初中"}, {"id": 2, "text": "高中"}, {"id": 3, "text": "专科"}, {"id": 4, "text": "本科"}, {"id": 5, "text": "硕士研究生"}, {"id": 6, "text": "博士研究生"}], "rule": {"msg": "请选择亲属三教育背景", "type": "null"}, "type": "picker", "label": "亲属三教育背景", "output": "text", "placeholder": "请选择亲属三教育背景", "defaultValue": ""}, {"id": "kinsfolkPhone3", "data": [], "rule": {"msg": "请输入亲属三本人手机号", "type": "null"}, "type": "input", "label": "亲属三本人手机号", "inputType": "Number", "maxlength": 11, "placeholder": "请输入亲属三本人手机号"}, {"id": "workUnit3", "data": [], "rule": {"msg": "请输入亲属三工作单位", "type": "null"}, "type": "input", "label": "亲属三工作单位", "inputType": "text", "placeholder": "请输入亲属三工作单位"}, {"id": "workDuty3", "data": [], "rule": {"msg": "请输入亲属三工作职务", "type": "null"}, "type": "input", "label": "亲属三工作职务", "inputType": "text", "placeholder": "请输入亲属三工作职务"}, {"id": "kinsfolkEco3", "data": [{"id": 0, "text": "一万及以下"}, {"id": 1, "text": "一万至五万"}, {"id": 2, "text": "五万至十五万"}, {"id": 3, "text": "十五万至三十万"}, {"id": 4, "text": "三十万及以上"}], "rule": {"msg": "请选择亲属三年收入", "type": "null"}, "type": "picker", "label": "亲属三年收入", "placeholder": "点击选择亲属三年收入"}, {"id": "kinsfolkHealth3", "data": [], "rule": {"msg": "请输入亲属三健康状况", "type": "null"}, "type": "input", "label": "亲属三健康状况", "inputType": "text", "placeholder": "请输入亲属三健康状况"}], "formName": "家庭成员信息填报", "formIntro": "", "createDate": "2021/8/30 12:00:00"}', 1, 1, '2021-08-30 12:00:00', '2021-09-10 17:07:23', 1, 'class', '2021-08-18 17:07:27', '2021-08-18 17:08:29', null, null, 1),
    (4, '个人偏好', 'COUNS001', 'teacher', 'wOO1J9lsy4g', '{"formId": "4", "formInfo": [{"id": "interest", "rule": {"msg": "请输入你的兴趣爱好", "type": "notnull"}, "type": "textarea", "label": "个人兴趣爱好", "maxlength": -1, "placeholder": "点击输入你的兴趣爱好"}, {"id": "awardHistory", "rule": {"type": "null"}, "type": "textarea", "label": "高中获奖经历", "maxlength": -1, "placeholder": "点击输入你的高中获奖经历"}, {"id": "dutyHistory", "rule": {"type": "null"}, "type": "textarea", "label": "高中担任班干部情况", "maxlength": -1, "placeholder": ""}, {"id": "hopeDuty", "rule": {"type": "null"}, "type": "textarea", "label": "希望担任何种班级干部", "maxlength": -1, "placeholder": ""}, {"id": "expectation", "rule": {"type": "null"}, "type": "textarea", "label": "大学的期望和四年后的目标", "maxlength": -1, "placeholder": ""}, {"id": "saidText", "rule": {"type": "null"}, "type": "textarea", "label": "想对辅导员说的一句话", "maxlength": -1, "placeholder": ""}, {"id": "characterDescription", "rule": {"type": "null"}, "type": "textarea", "label": "对自己性格的描述", "maxlength": -1, "placeholder": ""}, {"id": "familyDescription", "rule": {"type": "null"}, "type": "textarea", "label": "对自己家庭的描述", "maxlength": -1, "placeholder": ""}], "formName": "个人偏好信息填报", "formIntro": "", "createDate": "2021/8/30 12:00:00"}', 1, 1, '2021-08-30 12:00:00', '2021-09-10 17:07:23', 1, 'class', '2021-08-18 17:07:27', '2021-08-18 17:08:57', null, null, 1),
    (5, '来校行程填报', 'COUNS001', 'teacher', 'F2fveFD4Z0Y', '{"formId": "4", "formInfo": [{"id": "comeBackOnTime", "data": [{"id": 0, "text": "是"}, {"id": 1, "text": "否"}], "rule": {"msg": "请选择是否可以按时报到", "reg": "", "type": "notnull"}, "type": "radio", "label": "本人能否按时报到", "defaultValue": ""}, {"id": "reason", "tip": "人工智能学院报到时间为: 9月7号", "data": [], "rule": {"type": "null"}, "type": "input", "label": "无法按时报到原因", "inputType": "text", "placeholder": "如无法按时按到, 请填写原因"}, {"id": "currentLocation", "data": [], "rule": {"msg": "请选择本人目前所在地", "type": "notnull"}, "type": "region", "label": "本人目前所在地", "placeholder": "请选择本人目前所在地"}, {"id": "departureLocation", "data": [], "rule": {"msg": "请选择出发地", "type": "notnull"}, "type": "region", "label": "出发地", "placeholder": "请选择出发地"}, {"id": "departureAddress", "data": [], "rule": {"msg": "请输入你的具体出发地址", "type": "notnull"}, "type": "input", "label": "具体出发地址", "inputType": "text", "placeholder": ""}, {"id": "vehicle", "data": [{"id": 0, "text": "飞机"}, {"id": 1, "text": "高铁"}, {"id": 2, "text": "火车"}, {"id": 3, "text": "邮轮"}, {"id": 4, "text": "大巴"}, {"id": 5, "text": "地铁"}, {"id": 6, "text": "公交"}, {"id": 7, "text": "私家车"}, {"id": 8, "text": "步行"}, {"id": 9, "text": "骑车"}], "rule": {"msg": "请选择你来校的交通工具", "type": "notnull"}, "type": "picker", "label": "来校交通工具", "output": "text", "placeholder": "点击选择你来校的交通工具", "defaultValue": ""}, {"id": "vehicleNumber", "data": [], "rule": {"type": "null"}, "type": "input", "label": "交通工具编号", "inputType": "text", "placeholder": "如: 高铁班次"}, {"id": "seatNumber", "data": [], "rule": {"type": "null"}, "type": "input", "label": "交通工具座位号", "inputType": "text", "placeholder": "如: 高铁座位号"}, {"id": "arrivalAddress", "data": [], "rule": {"msg": "来深到达地点", "type": "notnull"}, "type": "input", "label": "来深到达地点", "inputType": "text", "placeholder": "如: 深圳北站"}, {"id": "departureTime", "data": [], "rule": {"msg": "请选择预计出发时间", "type": "notnull"}, "type": "time", "label": "预计出发时间", "maxDate": 1633017599000, "minDate": 1630425600000, "timeType": "datetime", "currentDate": 1630972800000, "placeholder": "请选择预计出发时间"}, {"id": "arrivalTime", "data": [], "rule": {"msg": "请选择预计到达时间", "type": "notnull"}, "type": "time", "label": "预计到达时间", "maxDate": 1633017599000, "minDate": 1630425600000, "timeType": "datetime", "currentDate": 1630972800000, "placeholder": "请选择预计到达时间"}], "formName": "来校行程填报", "formIntro": "", "createDate": "2021/8/30 12:00:00"}', 1, 1, '2021-08-30 12:00:00', '2021-09-10 17:07:23', 1, 'class', '2021-08-18 17:07:27', '2021-08-18 17:10:45', null, null, 1);

-- 表单填报范围
INSERT INTO registration_system.event_form_permission (id, form_id, permission_id, create_user, update_user,
                                                       create_time, update_time, mark)
VALUES (1, 1, 7, null, null, '2023-08-30 12:58:57', '2024-08-30 12:58:57', 1),
       (2, 2, 7, null, null, '2023-08-30 12:58:57', '2024-08-30 12:58:57', 1),
       (3, 3, 7, null, null, '2023-08-30 12:58:57', '2024-08-30 12:58:57', 1),
       (4, 4, 7, null, null, '2023-08-30 12:58:57', '2024-08-30 12:58:57', 1),
       (5, 5, 7, null, null, '2023-08-30 12:58:57', '2024-08-30 12:58:57', 1);

-- 审核人序列信息
insert into event_form_reviewer (first_number, first_type)
values (2101001, 'student'), #1
       (2101002, 'student'), #2
       (2101003, 'student'), #3
       ('COUNS001', 'teacher'), #4
       ('COUNS001', 'teacher'); #5

-- 审核策略
insert into event_reviewer_strategy(form_key, strategy, strategy_value, reviewer_id)
values
    -- 助理辅导员
    ('3z9xhb1qHZ2', 'class', '22软工6班专升本', 1),
    ('3z9xhb1qHZ2', 'class', '22计科1班', 1),
    ('3z9xhb1qHZ2', 'class', '22AI2班', 2),
    ('3z9xhb1qHZ2', 'class', '22机械设计1班', 2),
    ('3z9xhb1qHZ2', 'class', '22自动化1班', 3),
    ('3z9xhb1qHZ2', 'class', '22电气1班', 3),
    -- 辅导员
    ('3z9xhb1qHZ2', 'class', '22软工6班专升本', 4),
    ('3z9xhb1qHZ2', 'class', '22计科1班', 5),
    ('3z9xhb1qHZ2', 'class', '22AI2班', 6),
    ('3z9xhb1qHZ2', 'class', '22机械设计1班', 7),
    ('3z9xhb1qHZ2', 'class', '22自动化1班', 8),
    ('3z9xhb1qHZ2', 'class', '22电气1班', 9),
-- 助理辅导员
    ('8beREQIxN2r', 'class', '22软工6班专升本', 1),
    ('8beREQIxN2r', 'class', '22计科1班', 1),
    ('8beREQIxN2r', 'class', '22AI2班', 2),
    ('8beREQIxN2r', 'class', '22机械设计1班', 2),
    ('8beREQIxN2r', 'class', '22自动化1班', 3),
    ('8beREQIxN2r', 'class', '22电气1班', 3),
-- 辅导员
    ('8beREQIxN2r', 'class', '22软工6班专升本', 4),
    ('8beREQIxN2r', 'class', '22计科1班', 5),
    ('8beREQIxN2r', 'class', '22AI2班', 6),
    ('8beREQIxN2r', 'class', '22机械设计1班', 7),
    ('8beREQIxN2r', 'class', '22自动化1班', 8),
    ('8beREQIxN2r', 'class', '22电气1班', 9),
-- 助理辅导员
    ('zo2I8y5H1lq', 'class', '22软工6班专升本', 1),
    ('zo2I8y5H1lq', 'class', '22计科1班', 1),
    ('zo2I8y5H1lq', 'class', '22AI2班', 2),
    ('zo2I8y5H1lq', 'class', '22机械设计1班', 2),
    ('zo2I8y5H1lq', 'class', '22自动化1班', 3),
    ('zo2I8y5H1lq', 'class', '22电气1班', 3),
-- 辅导员
    ('zo2I8y5H1lq', 'class', '22软工6班专升本', 4),
    ('zo2I8y5H1lq', 'class', '22计科1班', 5),
    ('zo2I8y5H1lq', 'class', '22AI2班', 6),
    ('zo2I8y5H1lq', 'class', '22机械设计1班', 7),
    ('zo2I8y5H1lq', 'class', '22自动化1班', 8),
    ('zo2I8y5H1lq', 'class', '22电气1班', 9),
-- 助理辅导员
    ('wOO1J9lsy4g', 'class', '22软工6班专升本', 1),
    ('wOO1J9lsy4g', 'class', '22计科1班', 1),
    ('wOO1J9lsy4g', 'class', '22AI2班', 2),
    ('wOO1J9lsy4g', 'class', '22机械设计1班', 2),
    ('wOO1J9lsy4g', 'class', '22自动化1班', 3),
    ('wOO1J9lsy4g', 'class', '22电气1班', 3),
-- 辅导员
    ('wOO1J9lsy4g', 'class', '22软工6班专升本', 4),
    ('wOO1J9lsy4g', 'class', '22计科1班', 5),
    ('wOO1J9lsy4g', 'class', '22AI2班', 6),
    ('wOO1J9lsy4g', 'class', '22机械设计1班', 7),
    ('wOO1J9lsy4g', 'class', '22自动化1班', 8),
    ('wOO1J9lsy4g', 'class', '22电气1班', 9),
-- 助理辅导员
    ('F2fveFD4Z0Y', 'class', '22软工6班专升本', 1),
    ('F2fveFD4Z0Y', 'class', '22计科1班', 1),
    ('F2fveFD4Z0Y', 'class', '22AI2班', 2),
    ('F2fveFD4Z0Y', 'class', '22机械设计1班', 2),
    ('F2fveFD4Z0Y', 'class', '22自动化1班', 3),
    ('F2fveFD4Z0Y', 'class', '22电气1班', 3),
-- 辅导员
    ('F2fveFD4Z0Y', 'class', '22软工6班专升本', 4),
    ('F2fveFD4Z0Y', 'class', '22计科1班', 5),
    ('F2fveFD4Z0Y', 'class', '22AI2班', 6),
    ('F2fveFD4Z0Y', 'class', '22机械设计1班', 7),
    ('F2fveFD4Z0Y', 'class', '22自动化1班', 8),
    ('F2fveFD4Z0Y', 'class', '22电气1班', 9);