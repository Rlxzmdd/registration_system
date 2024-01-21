package com.withmore.user.permission.utils;

import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.dto.PermissionNodeFilterDto;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.vo.permissionnode.PermissionNodeSimpleVo;
import com.withmore.user.student.dto.StudentPermissionDto;
import com.withmore.user.student.mapper.StudentMapper;
import com.withmore.user.student.mapper.StudentPermissionMapper;
import com.withmore.user.teacher.dto.TeacherPermissionDto;
import com.withmore.user.teacher.mapper.TeacherMapper;
import com.withmore.user.teacher.mapper.TeacherPermissionMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class PermissionConvert {

    private static StudentPermissionMapper studentPermissionMapper;

    private static TeacherPermissionMapper teacherPermissionMapper;

    private static StudentMapper studentMapper;

    private static TeacherMapper teacherMapper;

    @Autowired
    public void setStudentPermissionMapper(StudentPermissionMapper studentPermissionMapper) {
        PermissionConvert.studentPermissionMapper = studentPermissionMapper;
    }

    @Autowired
    public void setTeacherPermissionMapper(TeacherPermissionMapper teacherPermissionMapper) {
        PermissionConvert.teacherPermissionMapper = teacherPermissionMapper;
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        PermissionConvert.studentMapper = studentMapper;
    }

    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        PermissionConvert.teacherMapper = teacherMapper;
    }

    private Map<String, Map<String, Map<String, List<String>>>> permission = new HashMap<>();

    private Map<String, Map<String, Map<String, Map<String, Integer>>>> permissionMap = new HashMap<>();

    /*全匹配*/
    public static final String WILDCARD = "*";

    /*模糊匹配*/
    public static final String VAGUE = "%";

    private List<? extends PermissionNode> permissions;

    public PermissionConvert(List<? extends PermissionNode> permissions) {
        this.permissions = permissions;
    }

    /**
     * 添加到索引表
     * 用于索引过滤后的权限节点ID
     *
     * @param node
     */
    private void add2IndexMap(PermissionNode node) {
        String level1 = node.getLevel1();
        String level2 = node.getLevel2();
        String level3 = node.getLevel3();
        String level4 = node.getLevel4();
        if (!this.permissionMap.containsKey(level1)) {
            permissionMap.put(level1, new HashMap<>());
        }
        Map<String, Map<String, Map<String, Integer>>> map1 = permissionMap.get(level1);
        if (!map1.containsKey(level2)) {
            map1.put(level2, new HashMap<>());
        }
        Map<String, Map<String, Integer>> map2 = map1.get(level2);
        if (!map2.containsKey(level3)) {
            map2.put(level3, new HashMap<>());
        }
        Map<String, Integer> map3 = map2.get(level3);
        map3.put(level4, node.getId());
    }

    /**
     * 对权限进行排序
     */
    private void sort() {

        for (PermissionNode permission : permissions) {
            String level1 = permission.getLevel1();
            String level2 = permission.getLevel2();
            String level3 = permission.getLevel3();
            String level4 = permission.getLevel4();
            if (!this.permission.containsKey(level1)) {
                this.permission.put(level1, new HashMap<>());
            }

            Map<String, Map<String, List<String>>> map1 = this.permission.get(level1);
            if (!map1.containsKey(level2)) {
                map1.put(level2, new HashMap<>());
            }
            Map<String, List<String>> map2 = map1.get(level2);
            if (!map2.containsKey(level3)) {
                map2.put(level3, new ArrayList<>());
            }
            List<String> list = map2.get(level3);
            list.add(level4);
            add2IndexMap(permission);
        }
    }

    /**
     * 构建权限节点
     * 同时获取其在表中的ID
     *
     * @param level1
     * @param level2
     * @param level3
     * @param level4
     * @return
     */
    private PermissionNode builder(String level1, String level2, String level3, String level4) {
        Integer id = permissionMap.get(level1).get(level2).get(level3).get(level4);
        PermissionNode node = new PermissionNode().setLevel1(level1).setLevel2(level2).setLevel3(level3).setLevel4(level4);
        node.setId(id);
        return node;
    }

    /**
     * 过滤重复权限，以及覆盖权限
     *
     * @return 单一权限，如去重已覆盖的权限
     */
    private List<PermissionNode> filter() {
        List<PermissionNode> ps = new ArrayList<>();
        if (!permission.containsKey(WILDCARD)) {
            for (String level1 : permission.keySet()) {
                Map<String, Map<String, List<String>>> map2 = permission.get(level1);
                if (!map2.containsKey(WILDCARD)) {
                    for (String level2 : map2.keySet()) {
                        Map<String, List<String>> map3 = map2.get(level2);
                        if (!map3.containsKey(WILDCARD)) {
                            for (String level3 : map3.keySet()) {
                                List<String> list = map3.get(level3);
                                if (list.contains(WILDCARD)) {
                                    ps.add(builder(level1, level2, level3, WILDCARD));
                                } else {
                                    for (String level4 : list) {
                                        ps.add(builder(level1, level2, level3, level4));
                                    }
                                }
                            }
                        } else {
                            ps.add(builder(level1, level2, WILDCARD, WILDCARD));
                            break;
                        }
                    }
                } else {
                    // level2=* level3=19% 情况不可进行权限覆盖
                    Map<String, List<String>> level3Map = map2.get(WILDCARD);
                    if (level3Map.containsKey(WILDCARD)) {
                        ps.add(builder(level1, WILDCARD, WILDCARD, WILDCARD));
                    } else {
                        // 年级通配权限，如 level2 level3 都为* ，则依旧会权限覆盖 **
                        List<String> keys = new ArrayList<>();
                        level3Map.keySet().forEach(x -> {
                            if (x.contains("%")) {
                                keys.add(x);
                            }
                        });
                        keys.forEach(x -> {
                            ps.add(builder(level1, WILDCARD, x, WILDCARD));
                        });
                    }
                    break;
                }
            }
        } else {
            ps.add(builder(WILDCARD, WILDCARD, WILDCARD, WILDCARD));
        }
        return ps;
    }

    /**
     * 获取过滤后的覆盖权限
     *
     * @return
     */
    public List<PermissionNode> handler() {
        sort();
        return filter();
    }

    /**
     * 查询教师，学生的可访问权限节点
     * 自动根据Dto中的Type 决定DAO Mapper
     *
     * @param dto 身份凭据
     * @return
     */
    public static List<PermissionNode> convert2Nodes(AuthToken2CredentialDto dto) {
        if (Constant.TOKEN_USER_TYPE_STUDENT.equals(dto.getType())) {
            return convert2Nodes(dto, PermissionConvert.studentPermissionMapper);
        } else if (Constant.TOKEN_USER_TYPE_TEACHER.equals(dto.getType())) {
            return convert2Nodes(dto, PermissionConvert.teacherPermissionMapper);
        }
        return new ArrayList<>();
    }

    /**
     * 查询教师可访问权限节点
     * 转换用户身份凭据至权限节点
     * 需聚合StudentPermissionMapper进行权限查询
     *
     * @param dto    身份凭据
     * @param mapper
     * @return
     */
    public static List<PermissionNode> convert2Nodes(AuthToken2CredentialDto dto, StudentPermissionMapper mapper) {
        List<StudentPermissionDto> viewPermission = mapper.getStudentViewPermission(dto.getNumber());
        PermissionConvert convert = new PermissionConvert(viewPermission);
        return convert.handler();
    }

    /**
     * 查询教师可访问权限节点
     * 转换用户身份凭据至权限节点
     * 需聚合TeacherPermissionMapper进行权限查询
     *
     * @param dto    身份凭据
     * @param mapper
     * @return
     */
    public static List<PermissionNode> convert2Nodes(AuthToken2CredentialDto dto, TeacherPermissionMapper mapper) {
        List<TeacherPermissionDto> viewPermission = mapper.getTeacherViewPermission(dto.getNumber());
        PermissionConvert convert = new PermissionConvert(viewPermission);
        return convert.handler();
    }

    /**
     * 自动推断dto类型转化用户默认的原子数据权限节点
     *
     * @param dto 身份凭据
     * @return
     */
    public static PermissionNode convert2Node(AuthToken2CredentialDto dto) {
        if (Constant.TOKEN_USER_TYPE_STUDENT.equals(dto.getType())) {
            return convert2Node(dto, PermissionConvert.studentMapper);
        } else if (Constant.TOKEN_USER_TYPE_TEACHER.equals(dto.getType())) {
            return convert2Node(dto, PermissionConvert.teacherMapper);
        }
        return null;
    }

    /**
     * 将学生个人信息转换为权限节点
     * 学院.专业.班级.个人学号
     *
     * @param dto    身份凭据
     * @param mapper
     * @return
     */
    public static PermissionNode convert2Node(AuthToken2CredentialDto dto, StudentMapper mapper) {
        return mapper.getStudentAtomPermissionNode(dto.getNumber());
    }

    /**
     * 将教师个人信息转换为权限节点
     * 学院.*.*.*
     *
     * @param dto    身份凭据
     * @param mapper
     * @return
     */
    public static PermissionNode convert2Node(AuthToken2CredentialDto dto, TeacherMapper mapper) {
        return mapper.getTeacherAtomPermissionNode(dto.getNumber());
    }

    /**
     * 将PermissionNode 转换转为 PermissionNodeSimpleVo
     *
     * @param nodes 权限节点
     * @return
     */
    public static List<PermissionNodeSimpleVo> permissionNode2PermissionSimpleVo(List<PermissionNode> nodes) {
        List<PermissionNodeSimpleVo> list = new ArrayList<>();
        for (PermissionNode node : nodes) {
            list.add(new PermissionNodeSimpleVo()
                    .setCollege(node.getLevel1())
                    .setMajor(node.getLevel2())
                    .setClasses(node.getLevel3())
                    .setSingle(node.getLevel4())
                    .setPermissionId(node.getId()));
        }
        return list;
    }

    /**
     * 将用户可访问的数据权限节点原子参数列表转换为HashMap
     *
     * @param dtoList 原子节点DTO
     * @param nodes
     * @return
     */
    public static Map<String, Map<String, List<String>>> permissionNode2FilterDict(List<PermissionNodeFilterDto> dtoList, List<PermissionNode> nodes) {
        Map<String, Map<String, List<String>>> dict = new HashMap<>();
        for (PermissionNodeFilterDto dto : dtoList) {
            if (!dict.containsKey(dto.getCollege())) {
                dict.put(dto.getCollege(), new HashMap<>());
            }
            Map<String, List<String>> majorDict = dict.get(dto.getCollege());
            if (!majorDict.containsKey(dto.getMajor())) {
                majorDict.put(dto.getMajor(), new ArrayList<>());
            }
            List<String> classes = majorDict.get(dto.getMajor());
            classes.add(dto.getClasses());
        }
        for (PermissionNode node : nodes) {
            if (WILDCARD.equals(node.getLevel2()) && node.getLevel3().contains(VAGUE)) {
                if (!dict.containsKey(node.getLevel1())) {
                    dict.put(node.getLevel1(), new HashMap<>());
                }
                Map<String, List<String>> majorDict = dict.get(node.getLevel1());
                if (!majorDict.containsKey(node.getLevel2())) {
                    majorDict.put(node.getLevel2(), new ArrayList<>());
                }
                List<String> classes = majorDict.get(node.getLevel2());
                classes.add(node.getLevel3());
            }
        }
        return dict;
    }

    public static void main(String[] args) {
        List<StudentPermissionDto> permissions = new ArrayList<>();
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("人工智能").setLevel2("软件").setLevel3("软件1").setLevel4("*"));
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("电信").setLevel2("通讯").setLevel3("通讯1").setLevel4("*"));
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("电信").setLevel2("通讯").setLevel3("*").setLevel4("*"));
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("人工智能").setLevel2("软件").setLevel3("软件2").setLevel4("*"));
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("人工智能").setLevel2("软件").setLevel3("软件3").setLevel4("*"));
        permissions.add((StudentPermissionDto) new StudentPermissionDto().setLevel1("人工智能").setLevel2("软件").setLevel3("*").setLevel4("*"));
        PermissionConvert p = new PermissionConvert(permissions);
        List<PermissionNode> nodes = p.handler();
        for (PermissionNode n :
                nodes) {
            System.out.println(n.toString());
        }
    }

}
