package com.showing.backend.api.service;

import com.showing.backend.api.request.AddReviewReq;

public interface ReviewService {

    void addReview(Long userId, AddReviewReq req);

}
