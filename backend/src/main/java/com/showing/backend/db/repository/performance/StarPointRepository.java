package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.StarPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarPointRepository extends JpaRepository<StarPoint, Long> {
}
