package com.showing.backend.api.service;

import com.showing.backend.api.response.RankingRes;
import com.showing.backend.db.entity.Ranking;
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
    public RankingRes getRankingInfo(Long performanceId) {
        Ranking ranking = rankingRepository.findByPerformanceId(performanceId).orElse(null);
        RankingRes rankingRes;
        if(ranking == null){
            rankingRes = RankingRes.builder()
                    .performanceId(performanceId)
                    .ratingCount(0)
                    .averageRating(0.0)
                    .build();
        }else{
            rankingRes = RankingRes.builder()
                    .performanceId(performanceId)
                    .ratingCount(ranking.getRatingCount())
                    .averageRating(ranking.getAverageRating())
                    .build();
        }
        return rankingRes;
    }

}
