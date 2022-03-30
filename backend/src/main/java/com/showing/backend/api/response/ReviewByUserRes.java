package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ReviewByUserRes {

    @ApiModelProperty(name = "리뷰 Id", example = "1")
    Long reviewId;

    @ApiModelProperty(name = "리뷰 작성자 이름", example = "홍길동")
    String userNickName;

    @ApiModelProperty(name = "리뷰에 입력된 캐스팅 배우 이름 리스트", example = "['홍길동', '고길동']")
    List<String> reviewActorNameList;

    @ApiModelProperty(name = "리뷰 내용", example = "리뷰입니다.")
    String content;

    @ApiModelProperty(name = "공연 관람일", example = "2022-03-04")
    LocalDate viewDate;

}
