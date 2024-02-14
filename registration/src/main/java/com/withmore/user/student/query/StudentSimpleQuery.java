package com.withmore.user.student.query;

import com.javaweb.system.common.BaseQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentSimpleQuery extends BaseQuery {
    @Setter(AccessLevel.NONE)
    private String name;
    @Setter(AccessLevel.NONE)
    private String number;

    public void setName(String name) {
        this.name = "%" + name + "%";
    }

    public void setNumber(String number) {
        this.number = "%" + number + "%";
    }
}
