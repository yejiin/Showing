package com.showing.backend.db.repository.review;

import com.showing.backend.db.entity.review.ReviewActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewActorRepository extends JpaRepository<ReviewActor, Long> {
}
