package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("FavoriteActorResponse")
@AllArgsConstructor
@Getter
@Setter
public class FavActorRes {

    @ApiModelProperty(name = "배우 이름", example = "장혁")
    String actorName;

    @ApiModelProperty(name = "배우 이미지 링크", example = "http://k.kakaocdn.net/dn/")
    String actorImage;
    
}
