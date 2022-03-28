package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Actor;

import java.util.List;

public interface ActorService {

    List<ActorRes> getSeasonCastingList(Long seasonPlaydbId);

    void setFavoriteActorWeight(int weight, User user, Actor actor);

}
