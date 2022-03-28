package com.showing.backend.api.service;

import java.util.List;

public interface PerformanceService {

    List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint);

}
