package com.showing.backend.db.repository.performance;

import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface PerformanceRepositoryCustom {

    List<PerformanceRes> findByTypeAndStarPointAvg(int performanceType);

}
