package com.withmore.common.dto;

import com.withmore.common.utils.JwtUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthToken2CredentialDto {
    private String number;
    private String type;

    public static AuthToken2CredentialDto create(JwtUtil jwtUtil, String token) {
        return new AuthToken2CredentialDto()
                .setNumber(jwtUtil.getUserId(token))
                .setType(jwtUtil.getUserType(token));
    }
}
