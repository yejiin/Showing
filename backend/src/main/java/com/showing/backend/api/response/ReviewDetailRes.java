package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.*;
import java.util.List;

@Getter
@Setter
@Builder
public class ReviewDetailRes {

    @ApiModelProperty(name = "리뷰 Id", example = "1")
    Long reviewId;

    @ApiModelProperty(name = "리뷰 작성자 Id", example = "1")
    Long userId;

    @ApiModelProperty(name = "리뷰 작성자 이름", example = "홍길동")
    String userName;

    @ApiModelProperty(name = "공연 Id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이름", example = "절벽")
    String performanceName;

    @ApiModelProperty(name = "공연의 시즌 Id", example = "1")
    Long seasonId;

    @ApiModelProperty(name = "시즌 시작 일자", example = "2022-01-01")
    LocalDate startDate;

    @ApiModelProperty(name = "시즌 종료 일자", example = "2022-01-31")
    LocalDate endDate;

    @ApiModelProperty(name = "관람 일자", example = "2022-01-31")
    LocalDate viewDate;

    @ApiModelProperty(name = "관람 시각", example = "13:00:00")
    LocalTime viewTime;

    @ApiModelProperty(name = "관람 장소", example = "샤롯데시어터")
    String location;

    @ApiModelProperty(name = "리뷰에 입력된 캐스팅 배우 이름 리스트", example = "['홍길동', '고길동']")
    List<String> reviewActorNameList;

    @ApiModelProperty(name = "리뷰 내용", example = "리뷰입니다.")
    String content;

    @ApiModelProperty(name = "리뷰 작성일", example = "2022-03-04")
    LocalDateTime reviewCreateDate;

}
