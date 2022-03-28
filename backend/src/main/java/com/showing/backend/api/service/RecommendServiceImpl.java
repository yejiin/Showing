package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.db.repository.recommend.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;

    @Override
    public List<PerformanceRes> getRecommendPerformanceList(Long performanceId) {
        List<Long> performanceIdList = new ArrayList<>();
        performanceIdList.add(performanceId);

        // performanceId 공연에 대해 공연완료, 공연중, 공연예정 상태인 추천 공연을 15개 조회한다.
        return recommendRepository.getTopCountRandomRecommendListByPerformanceId(0, 15, performanceIdList);
    }

}
