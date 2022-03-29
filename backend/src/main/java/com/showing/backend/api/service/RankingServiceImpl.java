package com.showing.backend.api.service;

import com.showing.backend.db.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;

    /*
     * 특정 공연의 평균 별점 조회
     */
    @Override
    public float getStarPointAverage(Long performanceId) {
        return rankingRepository.findAverageRatingByPerformanceId(performanceId).orElse((float)0.0); //
    }

}
