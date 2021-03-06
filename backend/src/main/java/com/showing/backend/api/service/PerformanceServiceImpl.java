package com.showing.backend.api.service;

import com.showing.backend.api.response.PerformanceRes;
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

    @Override
    public List<Long> getPerformanceIdListByUserAndStarPoint(Long userId, int starPoint) {
        return performanceRepository.findByUserIdAndStarPointGreaterThanEqual(userId, starPoint);
    }

    /*
     * 공연 상세 조회
     */
    @Transactional
    @Override
    public Performance getPerformanceDetail(Long performanceId) {
        return performanceRepository.findById(performanceId).orElseThrow(() -> new NotFoundException(ErrorCode.PERFORMANCE_NOT_FOUND));
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
