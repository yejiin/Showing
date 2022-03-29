package com.showing.backend.api.service;

import com.showing.backend.api.response.SearchRes;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService{

    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional
    public List<SearchRes> getPerformanceList(String keyword) {
        List<Performance> performanceList = performanceRepository.findTop5ByPerformanceNameContaining(keyword);
        List<SearchRes> searchResList = new ArrayList<>();
        for (Performance performance : performanceList){
            SearchRes searchRes = SearchRes.builder()
                    .performanceId(performance.getId())
                    .performanceName(performance.getPerformanceName())
                    .PerformanceType(performance.getPerformanceType())
                    .build();
            searchResList.add(searchRes);
        }
        return searchResList;
    }
}
