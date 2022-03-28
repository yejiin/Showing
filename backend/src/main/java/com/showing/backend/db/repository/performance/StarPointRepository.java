package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.entity.performance.StarPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarPointRepository extends JpaRepository<StarPoint, Long>, StarPointRepositoryCustom {

    Optional<StarPoint> findByUserAndPerformance(User user, Performance performance);

}
