package com.showing.backend.db.repository.performance;

import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.api.response.SearchRes;

import java.util.List;

public interface PerformanceRepositoryCustom {

    List<Long> findByUserIdAndStarPointGreaterThanEqual(Long userId, int starPoint);

    List<PerformanceRes> findByTypeAndStarPointAvg(int performanceType);

    List<SearchRes> findByPerformanceNameContaining(String keyword);

    List<PerformanceRes> findByProceedFlagIs1(int count);

}
