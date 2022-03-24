package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Season findByPlaydbId(int id);
}
