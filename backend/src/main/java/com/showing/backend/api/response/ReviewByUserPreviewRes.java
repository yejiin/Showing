package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReviewByUserPreviewRes {

    @ApiModelProperty(name = "리뷰 Id", example = "1")
    Long reviewId;

    @ApiModelProperty(name = "리뷰 작성 사용자 Id", example = "1")
    Long userId;

    @ApiModelProperty(name = "공연 Id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이름", example = "절벽")
    String performanceName;

    @ApiModelProperty(name = "공연 사진", example = "http://ticketimage...")
    String performanceImage;

    @ApiModelProperty(name = "리뷰 작성 날짜", example = "2022-02-23")
    LocalDate reviewCreateDate;

}
