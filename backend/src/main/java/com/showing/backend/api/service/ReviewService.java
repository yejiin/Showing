package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.ReviewByUserPreviewRes;
import com.showing.backend.api.response.ReviewDetailRes;

import java.util.List;

public interface ReviewService {

    List<ReviewByUserPreviewRes> getReviewListByUserId(Long userId);

    ReviewDetailRes getReviewDetail(Long reviewId);

    void addReview(ReviewReq req);

}
