package com.showing.backend.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceByActorRes {

    @ApiModelProperty(name = "배우 Id", example = "1")
    Long actorId;

    @ApiModelProperty(name = "배우 이름", example = "홍길동")
    String actorName;

    @ApiModelProperty(name = "공연 정보")
    PerformanceRes performanceInfo;

}
