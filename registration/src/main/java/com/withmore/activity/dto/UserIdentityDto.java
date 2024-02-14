package com.withmore.activity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UserIdentityDto {
    @JsonAlias("id_card")
    private String idCard;

    private String name;
}
