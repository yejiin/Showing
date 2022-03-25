package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.UpdateRatingReq;

public interface RatingService {

    void addRating(AddRatingReq req);

    void updateRating(UpdateRatingReq req);
}
