package com.withmore.user.permission.utils;

import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.student.query.StudentListQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 权限节点
 * 对象2对象判断权限
 */
public class PermissionJudge {
    private static boolean prefixLevel3Match(String targetClass, String level3) {
        if (!level3.contains("%")) {
            return false;
        }
        String prefix = level3.substring(0, level3.length() - 1);
        return targetClass.startsWith(prefix);
    }

    public static boolean judge(StudentListQuery baseQuery, List<PermissionNode> nodes) {
        for (PermissionNode permissionNode : nodes) {
            if (permissionNode.getLevel1().equals(baseQuery.getCollege()) || permissionNode.getLevel1().equals(PermissionConvert.WILDCARD)) {
                if (permissionNode.getLevel2().equals(baseQuery.getMajor()) || permissionNode.getLevel2().equals(PermissionConvert.WILDCARD)) {
                    if (permissionNode.getLevel3().equals(baseQuery.getClasses()) || permissionNode.getLevel3().equals(PermissionConvert.WILDCARD) || prefixLevel3Match(baseQuery.getClasses(), permissionNode.getLevel3())) {
                        if (permissionNode.getLevel4().equals(PermissionConvert.WILDCARD)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public static boolean judgeClassList(List<PermissionNode> nodes) {
        List<Integer> exclude = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            PermissionNode permissionNode = nodes.get(i);
            // 针对个人的访问权限需内联排除
            if (!permissionNode.getLevel4().equals(PermissionConvert.WILDCARD)) {
                exclude.add(i);
            }
        }
        Collections.sort(exclude);
        Collections.reverse(exclude);
        for (Integer idx :
                exclude) {
            nodes.remove((int) idx);
        }
        // 如果权限被过滤完后，则判定无权限
        return nodes.size() != 0;
    }

    /**
     * 判定在查询指定的学生时，是否可能具有对该学生的访问权限
     *
     * @param stuNumber 查询学生学号
     * @param nodes     权限节点
     */
    public static boolean judge(String stuNumber, List<PermissionNode> nodes) {
        List<Integer> exclude = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            PermissionNode permissionNode = nodes.get(i);
            // 针对个人的访问权限,并且非目标学生的需内联排除
            if (!permissionNode.getLevel4().equals(PermissionConvert.WILDCARD) && !permissionNode.getLevel4().equals(stuNumber)) {
                exclude.add(i);
            }
        }
        for (Integer idx :
                exclude) {
            nodes.remove((int) idx);
        }
        // 如果权限被过滤完后，则判定无权限
        return nodes.size() != 0;
    }
}
