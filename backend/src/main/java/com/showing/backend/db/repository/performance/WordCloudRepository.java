package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.WordCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordCloudRepository extends JpaRepository<WordCloud, Long> {

    /**
     * wordcloud table 에서 performanceId 공연의 가중치 높은 순으로 15개 조회한다.
     */
    List<WordCloud> findTop15ByPerformanceIdOrderByWeightDesc(Long performanceId);

}
