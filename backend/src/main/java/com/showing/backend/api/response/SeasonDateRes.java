package com.showing.backend.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@ApiModel("SeasonResponse")
@Getter
@Setter
@Builder
public class SeasonDateRes {

    @ApiModelProperty(name = "시즌별 공연 id", example = "1")
    Long seasonId;

    @ApiModelProperty(name = "공연 시작일시", example = "2022-3-24")
    LocalDate startDate;

    @ApiModelProperty(name = "공연 종료일시", example = "2022-4-8")
    LocalDate endDate;
}
