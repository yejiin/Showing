package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.PreviewReviewByUserRes;
import com.showing.backend.api.response.ReviewDetailRes;

import java.util.List;

public interface ReviewService {

    List<PreviewReviewByUserRes> getPreviewReviewListByUserId(Long userId);

    ReviewDetailRes getReviewDetail(Long reviewId);

    void addReview(ReviewReq req);

    void modifyReview(Long reviewId, ReviewReq req);

    void deleteReview(Long reviewId);

}
