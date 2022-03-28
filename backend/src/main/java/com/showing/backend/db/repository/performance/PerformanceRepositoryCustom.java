package com.showing.backend.db.repository.performance;

import java.util.List;

public interface PerformanceRepositoryCustom {

    List<Long> findByUserIdAndStarPointGreaterThanEqual(Long userId, int starPoint);

}