package com.showing.backend.api.service;

import com.showing.backend.api.response.SearchRes;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService{

    private final PerformanceRepository performanceRepository;

    /*
     * 공연 최근 시작일 순으로 keyword가 포함된 공연 조회
     */
    @Override
    public List<SearchRes> getPerformanceList(String keyword) {
        return performanceRepository.findByPerformanceNameContaining(keyword);
    }
}
