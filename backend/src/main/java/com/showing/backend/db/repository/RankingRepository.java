package com.showing.backend.db.repository;

import com.showing.backend.db.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    /*
     * 공연의 평균 별점 조회
     */
    Optional<Ranking> findByPerformanceId(Long performanceId);

}
