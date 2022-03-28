package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.ModifyRatingReq;
import com.showing.backend.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    void addRating(AddRatingReq req);

    void modifyRating(ModifyRatingReq req);

    void deleteRating(Long starId);

    Optional<Long> getRatingCount(User user, int type);

}
