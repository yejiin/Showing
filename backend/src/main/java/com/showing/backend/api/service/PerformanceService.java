package com.showing.backend.api.service;

import java.util.List;
import com.showing.backend.api.response.PerformanceDetailRes;

public interface PerformanceService {

    List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint);

    PerformanceDetailRes getPerformanceDetail(Long userId, Long performancdId);

}
