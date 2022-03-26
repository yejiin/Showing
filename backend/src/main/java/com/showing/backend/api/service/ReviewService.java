package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.*;

import java.util.List;

public interface ReviewService {

    List<PreviewReviewByUserRes> getPreviewReviewListByUserId(Long userId);

    List<PreviewReviewByPerformanceRes> getPreviewReviewListByPerformanceId(Long performanceId);

    ReviewDetailRes getReviewDetail(Long reviewId);

    void addReview(ReviewReq req);

    void modifyReview(Long reviewId, ReviewReq req);

    void deleteReview(Long reviewId);

}
