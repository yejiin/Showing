package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("TokenResponse")
@Getter
@Setter
@AllArgsConstructor
public class TokenRes {

    @ApiModelProperty(name = "액세스 토큰", example = "JWT token 값")
    private String accessToken;

    @ApiModelProperty(name = "리프레쉬 토큰", example = "JWT token 값")
    private String refreshToken;
}
