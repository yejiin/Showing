package com.showing.backend.api.service;

import com.showing.backend.api.response.SeasonDateRes;
import com.showing.backend.api.response.SeasonRes;

import java.util.List;

public interface SeasonService {

    List<SeasonDateRes> getSeasonList(Long performanceId);

    SeasonRes getSeasonInfo(Long seasonId);
}
