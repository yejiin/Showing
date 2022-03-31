package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.*;
import com.showing.backend.db.entity.review.Review;

import java.util.List;

public interface ReviewService {

    List<PreviewReviewByUserRes> getPreviewReviewListByUserId(Long userId);

    List<PreviewReviewByPerformanceRes> getPreviewReviewListByPerformanceId(Long performanceId);

    List<ReviewByUserRes> getReviewListByPerformanceIdAndUserId(Long performanceId, Long userId);

    List<ReviewBySeasonRes> getReviewListBySeasonId(Long seasonId);

    ReviewDetailRes getReviewDetail(Long reviewId);

    void addReview(ReviewReq req);

    void modifyReview(Long reviewId, ReviewReq req);

    void deleteReview(Long reviewId);

}
