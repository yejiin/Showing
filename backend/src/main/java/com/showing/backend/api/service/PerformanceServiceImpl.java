package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceDetailRes;
import com.showing.backend.api.response.PreviewReviewByPerformanceRes;
import com.showing.backend.api.response.SeasonRes;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Override
    public List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint) {
        return performanceRepository.findByUserIdAndStarPointGreaterThanEqual(userId, starPoint);
    }

    private final PerformanceRepository performanceRepository;
    private final SeasonService seasonService;
    private final RatingService ratingService;
    private final ReviewService reviewService;

    @Transactional
    @Override
    public PerformanceDetailRes getPerformanceDetail(Long userId, Long performancdId) {
        Performance performance = performanceRepository.findById(performancdId).orElseThrow(() -> new NotFoundException(ErrorCode.PERFORMANCE_NOT_FOUND));
        int rating = ratingService.getRating(userId, performancdId);
        SeasonRes seasonRes = seasonService.getSeasonInfo(performance.getLastSeasonId());
        List<PreviewReviewByPerformanceRes> previewReviewList = reviewService.getPreviewReviewListByPerformanceId(performancdId);

        return PerformanceDetailRes.of(performance,rating,seasonRes,previewReviewList);
    }
}
