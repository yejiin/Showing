package com.showing.backend.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("ModifyRatingReq")
@Getter
@Setter
public class ModifyRatingReq {

    @ApiModelProperty(name = "유저의 Id", example = "1")
    private Long userId;

    @ApiModelProperty(name = "별점 Id", example = "1")
    private Long starId;

    @ApiModelProperty(name = "별점", example = "5")
    private int rating;

}
