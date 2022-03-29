package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long>, PerformanceRepositoryCustom {

    /**
     * performance name에 keyword가 포함된 모든 공연을 조회한다.
     */
    List<Performance> findTop5ByPerformanceNameContaining(String keyword);

}
