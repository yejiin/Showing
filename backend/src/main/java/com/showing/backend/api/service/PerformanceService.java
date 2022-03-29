package com.showing.backend.api.service;

import java.util.List;
import com.showing.backend.api.response.PerformanceDetailRes;
import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface PerformanceService {

    List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint);

    PerformanceDetailRes getPerformanceDetail(Long userId, Long performancdId);

    List<PerformanceRes> getPerformanceListByStarPointAvg(int performanceType);

}
