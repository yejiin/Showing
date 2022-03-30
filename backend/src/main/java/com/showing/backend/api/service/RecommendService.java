package com.showing.backend.api.service;

import com.showing.backend.api.response.*;

import java.util.List;

public interface RecommendService {

    List<PerformanceRes> getRecommendPerformanceList(int type, List<Long> performanceIdList);

    List<PerformanceRes> getRecommendPerformanceListByUser(Long userId, int starPoint);

    List<RecommendByActorRes> getFavoriteActorPerformanceListByUser(Long userId);

    RecommendRes getRecommendList(Long userId);

}
