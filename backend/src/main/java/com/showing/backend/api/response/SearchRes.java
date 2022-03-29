package com.showing.backend.api.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel("SearchResponse")
@Getter
@Setter
@Builder
public class SearchRes {

    @ApiModelProperty(name = "공연 id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이름", example = "데스노트")
    String performanceName;

    @ApiModelProperty(name = "공연 타입", example = "1")
    Integer PerformanceType;

}
