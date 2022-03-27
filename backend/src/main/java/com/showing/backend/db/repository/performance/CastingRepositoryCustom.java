package com.showing.backend.db.repository.performance;

import com.showing.backend.api.response.ActorRes;

import java.util.List;
import java.util.Optional;

public interface CastingRepositoryCustom {
    Optional<List<ActorRes>> getCastingBySeason(Long seasonPlaydbId);
}
