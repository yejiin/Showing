package com.showing.backend.db.repository.performance;

import com.showing.backend.db.entity.performance.WordCloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCloudRepository extends JpaRepository<WordCloud, Long> {
}
