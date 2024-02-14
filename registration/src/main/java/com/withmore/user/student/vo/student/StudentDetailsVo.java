package com.withmore.user.student.vo.student;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentDetailsVo {
    /*所属学院*/
    private String college;
    /*姓名*/
    private String name;
    /*所属班级*/
    private String classes;
    /*学号*/
    private String number;
    /*身份证*/
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String idCard;

    public void setIdCard(String idCard) {
        int start = 4;
        int end = idCard.length() - 4;
        StringBuilder buffer = new StringBuilder(idCard);
        for (int i = start; i < end; i++) {
            buffer.setCharAt(i, '*');
        }
        this.idCard = buffer.toString();
    }

    public String getIdCard() {
        return "";
    }

    /*性别*/
    private String gender;
    /*民族*/
    private String nation;
    /*邮箱*/
    private String email;
    /*联系电话*/
    private String phone;
    /*政治面貌*/
    private String police;
    /*微信号*/
    private String wechat;
    /*QQ*/
    private String qq;
    /*头像链接*/
    private String avatar;
    /*宿舍*/
    private String dormitory;
}
