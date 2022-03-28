package com.showing.backend.db.repository;

import com.showing.backend.db.entity.PerformanceTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceTagRepository extends JpaRepository<PerformanceTag, Long> {
}
