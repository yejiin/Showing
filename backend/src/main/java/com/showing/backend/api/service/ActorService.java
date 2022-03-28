package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.db.entity.User;

import java.util.List;

public interface ActorService {
    List<ActorRes> getSeasonCastingList(Long seasonPlaydbId);

    List<FavActorRes> getFavoriteActorList(User user);
}
