package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface RecommendService {

    List<PerformanceRes> getRecommendPerformanceList(Long performanceId);

}
