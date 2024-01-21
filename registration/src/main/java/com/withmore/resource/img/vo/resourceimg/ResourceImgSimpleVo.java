package com.withmore.resource.img.vo.resourceimg;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceImgSimpleVo {
    private String uuid;
    private String url;
}
