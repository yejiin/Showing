package com.showing.backend.db.repository;

import com.showing.backend.db.entity.PerformanceTag;
import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceTagRepository extends JpaRepository<PerformanceTag, Long> {

    /**
     * tag table 에서 performanceId 로 해당 performance의 태그를 모두 조회한다.
     */
    List<PerformanceTag> findAllByPerformance(Performance performance);
}
