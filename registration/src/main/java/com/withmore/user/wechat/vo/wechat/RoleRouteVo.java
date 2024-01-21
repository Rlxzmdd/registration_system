package com.withmore.user.wechat.vo.wechat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleRouteVo {
    // private Integer roleId;
    private String routeName;
    private String icon;
    private String type;
    private String urlName;
}
