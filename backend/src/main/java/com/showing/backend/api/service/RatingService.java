package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.ModifyRatingReq;
import com.showing.backend.api.response.RatingRes;
import com.showing.backend.db.entity.User;

import java.util.List;

public interface RatingService {

    Long addRating(AddRatingReq req);

    void modifyRating(ModifyRatingReq req);

    void deleteRating(Long starId);

    RatingRes getRating(Long userId, Long performanceId);

    Long getRatingCount(User user, int type);

    List<Long> getRatingRatio(User user);

    Double getRatingAvg(User user);
}
