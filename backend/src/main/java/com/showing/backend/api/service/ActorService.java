package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;

import java.util.List;

public interface ActorService {
    List<ActorRes> getSeasonCastingList(Long seasonPlaydbId);
}
