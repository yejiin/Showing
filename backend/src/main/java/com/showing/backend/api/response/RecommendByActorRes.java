package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendByActorRes {

    @ApiModelProperty(name = "배우 Id", example = "1")
    Long actorId;

    @ApiModelProperty(name = "배우 이름", example = "홍길동")
    String actorName;

    @ApiModelProperty(name = "공연 정보")
    List<PerformanceRes> performanceInfoList;

}
