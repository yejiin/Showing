package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("FavoriteTagResponse")
@Getter
@Setter
@AllArgsConstructor
public class FavTagRes {

    @ApiModelProperty(name = "태그 이름", example = "연기력")
    String tagName;

    @ApiModelProperty(name = "태그 선호 가중치", example = "1.5555")
    Float tagWeight;
}
