package com.showing.backend.db.repository.recommend;

import com.showing.backend.api.response.PerformanceByActorRes;
import com.showing.backend.api.response.PerformanceRes;

import java.util.List;

public interface RecommendRepositoryCustom {

    /**
     * recommend 테이블에서 해당 공연의 추천 공연 리스트를 랜덤으로 count개 조회한다.
     */
    List<PerformanceRes> getTopCountRandomRecommendListByPerformanceId(int type, int count, List<Long> performanceIdList);

    /**
     * 사용자의 top5 선호 배우 중 한명을 랜덤으로 골라 출연 공연 리스트를 조회한다.
     */
    List<PerformanceByActorRes> getPerformanceListRandomFavoriteActorId(Long actorId);

}
