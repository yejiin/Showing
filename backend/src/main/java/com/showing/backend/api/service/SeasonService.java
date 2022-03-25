package com.showing.backend.api.service;

import com.showing.backend.api.response.SeasonRes;

public interface SeasonService {
    SeasonRes getSeasonInfo(Long seasonId);
}
