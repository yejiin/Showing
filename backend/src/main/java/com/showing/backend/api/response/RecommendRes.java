package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRes {

    @ApiModelProperty(name = "사용자 맞춤 추천 공연 리스트")
    List<PerformanceRes> recommendListByUser;

    @ApiModelProperty(name = "선호 배우 2명의 출연작 리스트")
    List<RecommendByActorRes> recommendListByActor;

}
