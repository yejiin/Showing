package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("KakaoLoginResponse")
@Getter
@Setter
@AllArgsConstructor
public class LoginRes {

    @ApiModelProperty(name = "유저 ID", example = "1")
    private Long id;

    @ApiModelProperty(name = "액세스 토큰", example = "JWT token 값")
    private String accessToken;

    @ApiModelProperty(name = "리프레쉬 토큰", example = "JWT token 값")
    private String refreshToken;

    @ApiModelProperty(name = "유저 닉네임", example = "김싸피")
    private String nickName;

    @ApiModelProperty(name = "유저 프로필 사진 URI", example = "http//uri...")
    private String userImage;
}
