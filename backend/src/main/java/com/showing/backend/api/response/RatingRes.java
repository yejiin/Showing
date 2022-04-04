package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RatingResponse")
@Getter
@Setter
@Builder
public class RatingRes {

    @ApiModelProperty(name = "별점의 Id", example = "1")
    Long starId;

    @ApiModelProperty(name = "별점", example = "5")
    int rating;

}
