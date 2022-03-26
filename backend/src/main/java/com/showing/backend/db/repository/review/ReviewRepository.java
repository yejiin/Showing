package com.showing.backend.db.repository.review;

import com.showing.backend.api.response.ReviewByUserPreviewRes;
import com.showing.backend.db.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * review table 에서 userId가 작성한 모든 리뷰를 조회한다.
     */
    List<Review> findByUserId(Long userId);

}
