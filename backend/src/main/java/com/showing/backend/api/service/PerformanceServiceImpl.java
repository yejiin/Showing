package com.showing.backend.api.service;

import com.showing.backend.api.response.*;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final SeasonService seasonService;
    private final ReviewService reviewService;
    private final RankingService rankingService;

    @Override
    public List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint) {
        return performanceRepository.findByUserIdAndStarPointGreaterThanEqual(userId, starPoint);
    }

    /*
     * 공연 상세 조회
     */
    @Transactional
    @Override
    public PerformanceDetailRes getPerformanceDetail(Long performancdId) {
        Performance performance = performanceRepository.findById(performancdId).orElseThrow(() -> new NotFoundException(ErrorCode.PERFORMANCE_NOT_FOUND));
        RankingRes rankingRes = rankingService.getRankingInfo(performancdId);
        SeasonRes seasonRes = seasonService.getSeasonInfo(performance.getLastSeasonId());
        List<PreviewReviewByPerformanceRes> previewReviewList = reviewService.getPreviewReviewListByPerformanceId(performancdId);

        return PerformanceDetailRes.of(performance,rankingRes,seasonRes,previewReviewList);
    }

    /*
     * 공연 타입 별 평균 별점 순으로 공연 목록 조회
     */
    @Override
    public List<PerformanceRes> getPerformanceListByStarPointAvg(int performanceType) {
        // performanceType = 1 (뮤지컬), 2 (연극)
        return performanceRepository.findByTypeAndStarPointAvg(performanceType);
    }
}
