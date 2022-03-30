package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RankingResponse")
@Getter
@Setter
@Builder
public class RankingRes {

    @ApiModelProperty(name = "공연 id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 평가 인원", example = "50")
    int ratingCount;

    @ApiModelProperty(name = "공연 평균 별점", example = "4.5")
    double averageRating;
}
