package com.showing.backend.api.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PerformanceRes {

    Long performanceId;

    String performanceName;

    // 공연 종류 (0: 뮤지컬, 1: 연극)
    int performanceType;

    Long lastSeasonId;

    String lastSeasonImage;

    LocalDate lastSeasonStartDate;

    LocalDate lastSeasonEndDate;

    // 러닝 여부 (0: 공연완료, 1: 공연중, 2: 공연예정)
    int lastSeasonProceedFlag;

    double starPointAverage;

}
