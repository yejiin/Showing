package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.ModifyRatingReq;

public interface RatingService {

    void addRating(AddRatingReq req);

    void modifyRating(ModifyRatingReq req);

    void deleteRating(Long starId);

    int getRating(Long userId, Long performanceId);

}
