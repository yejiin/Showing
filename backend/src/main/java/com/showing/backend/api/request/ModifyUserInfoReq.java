package com.showing.backend.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel("ModifyUserInfoReq")
@Getter
@AllArgsConstructor
public class ModifyUserInfoReq {

    @ApiModelProperty(name = "유저 아이디", example = "1")
    private Long userId;

    @ApiModelProperty(name = "유저 닉네임", example = "김싸피")
    private String nickName;

    @ApiModelProperty(name = "유저 소개글", example = "안녕하세요~")
    private String introduce;

    @ApiModelProperty(name = "유저 프로필 사진 URI", example = "http//uri...")
    private String userImage;
}
