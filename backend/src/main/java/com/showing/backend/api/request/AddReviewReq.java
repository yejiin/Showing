package com.showing.backend.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApiModel("AddReviewReq")
@Getter
@Setter
public class AddReviewReq {

    @ApiModelProperty(name = "공연의 시즌 Id", example = "1")
    private Long seasonId;

    @ApiModelProperty(name = "공연을 관람한 날짜", example = "2022-03-24")
    private LocalDate showDate;

    @ApiModelProperty(name = "관람한 공연의 시작 시각", example = "16:00")
    private LocalTime showTime;

    @ApiModelProperty(name = "리뷰 내용", example = "리뷰입니다.")
    private String reviewContent;

    @ApiModelProperty(name = "관람한 공연의 캐스팅 배우 Id 리스트", example = "[1, 2, 3]")
    private List<Long> castingIdList;

}
