package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    /**
     * season table 에서 performanceId 공연의 모든 시즌을 조회한다.
     */
    List<Season> findAllByPerformanceId(Long performanceId);

}
