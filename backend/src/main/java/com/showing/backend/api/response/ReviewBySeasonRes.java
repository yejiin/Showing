package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ReviewBySeasonRes {

    @ApiModelProperty(name = "리뷰 Id", example = "1")
    Long reviewId;

    @ApiModelProperty(name = "리뷰 작성자 Id", example = "1")
    Long userId;

    @ApiModelProperty(name = "리뷰 작성자 이름", example = "홍길동")
    String userName;

    @ApiModelProperty(name = "리뷰 작성자 프로필 사진", example = "http://kakao...")
    String userImage;

    @ApiModelProperty(name = "공연 Id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이름", example = "절벽")
    String performanceName;

    @ApiModelProperty(name = "캐스팅 배우 이름 리스트", example = "['홍길동', '고길동']")
    List<String> castingActorNameList;

    @ApiModelProperty(name = "리뷰 내용", example = "리뷰입니다.")
    String content;

    @ApiModelProperty(name = "리뷰 작성일", example = "2022-03-04")
    LocalDate reviewCreateDate;

}
