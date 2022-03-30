package com.showing.backend.db.repository.review;

import com.showing.backend.db.entity.review.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getReviewByPerformanceAndUser(Long performanceId, Long userId);
}
