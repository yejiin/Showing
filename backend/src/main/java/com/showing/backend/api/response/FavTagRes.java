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

    @ApiModelProperty(name = "단어", example = "배우")
    String name;

    @ApiModelProperty(name = "가중치", example = "10")
    int value;
}
