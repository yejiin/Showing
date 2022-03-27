package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("ActorResponse")
@AllArgsConstructor
@Getter
@Setter
public class ActorRes {

    @ApiModelProperty(name = "배우 id", example = "1")
    Long actorId;

    @ApiModelProperty(name = "배역", example = "이대길")
    String role;

    @ApiModelProperty(name = "배우 이미지 링크", example = "http://k.kakaocdn.net/dn/")
    String actorImage;

    @ApiModelProperty(name = "배우 이름", example = "장혁")
    String actorName;

}
