package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceByActorRes;
import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface RecommendService {

    List<PerformanceRes> getRecommendPerformanceList(int type, List<Long> performanceIdList);

    List<PerformanceRes> getRecommendPerformanceListByUser(Long userId, int starPoint);

    PerformanceByActorRes getFavoriteActorPerformanceListByUser(Long userId);

}
