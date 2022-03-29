package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("MainPerformanceListResponse")
@Getter
@Setter
@AllArgsConstructor
public class MainPerformanceListRes {

    // TODO 선호 배우, 추천 공연 리스트 추가 필요

    @ApiModelProperty(name = "뮤지컬 평균 별점 순위")
    List<PerformanceRes> musicalAvgList;

    @ApiModelProperty(name = "연극 평균 별점 순위")
    List<PerformanceRes> playAvgList;

}
