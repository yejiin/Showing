package com.showing.backend.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("AddRatingReq")
@Getter
@Setter
public class AddRatingReq {

    @ApiModelProperty(name = "유저의 Id", example = "1")
    private Long userId;

    @ApiModelProperty(name = "공연의 시즌 Id", example = "1")
    private Long seasonId;

    @ApiModelProperty(name = "별점", example = "5")
    private int rating;
}
