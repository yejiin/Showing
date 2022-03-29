package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceDetailRes;
import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface PerformanceService {

    PerformanceDetailRes getPerformanceDetail(Long userId, Long performancdId);

    List<PerformanceRes> getPerformanceListByStarPointAvg(int performanceType);

}
