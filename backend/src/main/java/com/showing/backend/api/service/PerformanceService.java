package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceDetailRes;

public interface PerformanceService {

    PerformanceDetailRes getPerformanceDetail(Long userId, Long performancdId);

}
