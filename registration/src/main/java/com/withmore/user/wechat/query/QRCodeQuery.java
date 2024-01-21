package com.withmore.user.wechat.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;

@Data
public class QRCodeQuery extends BaseQuery {
    /**
     * 二维码图像宽度
     */
    private Integer width;
    /**
     * 二维码图像高度
     */
    private Integer height;
}
