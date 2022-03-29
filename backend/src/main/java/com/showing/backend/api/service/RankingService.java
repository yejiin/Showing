package com.showing.backend.api.service;

import com.showing.backend.api.response.RankingRes;

public interface RankingService {

    RankingRes getRankingInfo(Long performanceId);

}
