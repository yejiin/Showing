package com.showing.backend.db.repository.recommend;

import com.showing.backend.db.entity.recommend.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}
