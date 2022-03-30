package com.showing.backend.api.service;

import java.util.List;
import com.showing.backend.api.response.PerformanceDetailRes;
import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.db.entity.performance.Performance;

import java.util.List;

public interface PerformanceService {

    List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint);

    Performance getPerformanceDetail(Long performanceId);

    List<PerformanceRes> getPerformanceListByStarPointAvg(int performanceType);

}
