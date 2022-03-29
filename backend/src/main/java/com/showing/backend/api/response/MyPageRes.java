package com.showing.backend.api.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("MyPageResponse")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyPageRes {

    @ApiModelProperty(name = "유저 닉네임", example = "김싸피")
    private String nickName;

    @ApiModelProperty(name = "유저 이메일", example = "hello@naver.com")
    private String email;

    @ApiModelProperty(name = "유저 소개글", example = "안녕하세요~")
    private String introduce;

    @ApiModelProperty(name = "유저 프로필 사진 URI", example = "http//uri...")
    private String userImage;

    @ApiModelProperty(name = "선호 배우 리스트", example = "['홍길동', '고길동']")
    private List<FavActorRes> favoriteActorList;

    @ApiModelProperty(name = "선호 태그 리스트", example = "['연기력', '판타지']")
    private List<FavTagRes> favoriteTagList;

    @ApiModelProperty(name = "뮤지컬 평가 수", example = "20")
    private Long muscialCnt;

    @ApiModelProperty(name = "연극 평가 수", example = "10")
    private Long playCnt;

    @ApiModelProperty(name = "별점 별 분포 (0.5 부터 10.0까지 별점 당 평가 갯수) ", example = "[0,20,33,20,...9]")
    private List<Long> ratingCntList;

    @ApiModelProperty(name = "별점 평균", example = "3.5")
    private Double ratingAvg;

    @ApiModelProperty(name = "평가 리스트", example = "")
    private List<MyPerformanceRes> performanceList;

}
