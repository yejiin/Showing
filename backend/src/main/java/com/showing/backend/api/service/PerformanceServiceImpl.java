package com.showing.backend.api.service;

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

}
