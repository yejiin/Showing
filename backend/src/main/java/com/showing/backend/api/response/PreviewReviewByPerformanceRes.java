package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
public class PreviewReviewByPerformanceRes {

    @ApiModelProperty(name = "리뷰 Id", example = "1")
    Long reviewId;

    @ApiModelProperty(name = "리뷰 작성자 Id", example = "1")
    Long userId;

    @ApiModelProperty(name = "리뷰 작성자 이름", example = "홍길동")
    String userName;

    @ApiModelProperty(name = "리뷰 작성자 프로필 사진", example = "http://k.kakaocdn...")
    String userImage;

    @ApiModelProperty(name = "리뷰 내용", example = "리뷰입니다.")
    String content;

}
